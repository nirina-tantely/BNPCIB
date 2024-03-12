package org.bnpcib.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bnpcib.exception.TechnicalException;
import org.bnpcib.model.*;
import org.bnpcib.util.JsonUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class GenerateSummaryServiceImpl implements GenerateSummaryService {

    private static final Logger logger = LogManager.getLogger(GenerateSummaryServiceImpl.class);

    @Override
    public void generateCustomersSummariesTrips(String inputFilePath, String outputFilePath) {
        InputData input;
        try (final InputStream is = new FileInputStream(inputFilePath)) {
            input = JsonUtils.fromJson(is, InputData.class);
        } catch (IOException e) {
            throw new TechnicalException("Error when reading input file!" + e.getMessage(), e);
        }
        //Get taps by customer
        Map<Integer, List<Tap>> tapsByCustomer = input.getTaps().stream().map(Tap::validate).collect(Collectors.groupingBy(Tap::getCustomerId));
        List<CustomerSummary> customerSummaries = new ArrayList<>();
        tapsByCustomer.forEach((customerId, taps) -> {
            List<Trip> trips = getTrips(taps);
            Integer totalCost = (Integer) trips.stream().mapToInt(Trip::getCostInCents).sum();
            CustomerSummary summary = CustomerSummary.CustomerSummaryBuilder.aCustomerSummary().withCustomerId(customerId)
                    .withTotalCostInCents(totalCost)
                    .withTrips(trips).build();
            customerSummaries.add(summary);
        });
        JsonUtils.toJson(OutputData.OutputDataBuilder.anOutputData().withCustomerSummaries(customerSummaries).build(), new File(outputFilePath));
    }

    private List<Trip> getTrips(List<Tap> taps) {
        AtomicInteger index = new AtomicInteger(0);
        Map<Tap, Tap> tapMap = new HashMap<>();
        AtomicReference<Tap> startTap = new AtomicReference<>();
        List<Trip> trips = new ArrayList<>();
        taps.stream().sorted(Comparator.comparing(Tap::getUnixTimestamp)).forEach(tap -> {
            if (index.getAndIncrement() % 2 == 0) {
                startTap.set(tap);
            } else {
                tapMap.put(startTap.get(), tap);
            }
        });
        tapMap.forEach((start, end) -> {
            if (start.getStation().equals(end.getStation())) {
                int zone = BNPConsts.STATIONS.get(start.getStation())[0];
                trips.add(Trip.TripBuilder.aTrip().withStationStart(start.getStation())
                        .withStationEnd(end.getStation())
                        .withStartedJourneyAt(start.getUnixTimestamp())
                        .withCostInCents(0)
                        .withZoneFrom(zone)
                        .withZoneTo(zone)
                        .build());
            } else {
                Travel travel = calculateTravelPrice(start.getStation(), end.getStation());
                trips.add(Trip.TripBuilder.aTrip().withStationStart(start.getStation())
                        .withStationEnd(end.getStation())
                        .withStartedJourneyAt(start.getUnixTimestamp())
                        .withCostInCents(travel.getPrice())
                        .withZoneFrom(travel.getStartZone())
                        .withZoneTo(travel.getEndZone())
                        .build());
            }
        });
        return trips;
    }

    private Travel calculateTravelPrice(String startStation, String endStation) {
        List<Travel> possiblePrices = new ArrayList<>();
        for (int startZone : BNPConsts.STATIONS.get(startStation)) {
            for (int endZone : BNPConsts.STATIONS.get(endStation)) {
                Travel tempTravel = Travel.TravelBuilder.aTravel().withStartZone(startZone).withEndZone(endZone).build();
                Optional<Travel> foundTravel = BNPConsts.TRAVELS_PRICING.stream().filter(tempTravel::equals).findFirst();
                if (foundTravel.isPresent()) {
                    possiblePrices.add(foundTravel.get());
                } else {
                    //Put the price 0 euros if the pricing rule is not defined
                    possiblePrices.add(Travel.TravelBuilder.aTravel().withStartZone(startZone).withEndZone(endZone).withPrice(0).build());
                }
            }
        }
        return possiblePrices.stream().min(Comparator.comparing(Travel::getPrice)).orElse(null);
    }


}
