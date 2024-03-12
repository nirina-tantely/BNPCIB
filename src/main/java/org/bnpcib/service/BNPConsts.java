package org.bnpcib.service;

import org.bnpcib.model.Travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BNPConsts {

    //Map that saves all stations with corresponding zones
    public static final Map<String, int[]> STATIONS = new HashMap<>();


    public static final List<Travel> TRAVELS_PRICING = new ArrayList<>();

    static {
        STATIONS.put("A", new int[]{1});
        STATIONS.put("B", new int[]{1});
        STATIONS.put("C", new int[]{2,3});
        STATIONS.put("D", new int[]{2});
        STATIONS.put("E", new int[]{2,3});
        STATIONS.put("F", new int[]{3,4});
        STATIONS.put("G", new int[]{4});
        STATIONS.put("H", new int[]{4});
        STATIONS.put("I", new int[]{4});

        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(1).withEndZone(2).withPrice(240).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(2).withEndZone(2).withPrice(240).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(1).withEndZone(1).withPrice(240).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(2).withEndZone(1).withPrice(240).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(3).withEndZone(4).withPrice(200).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(4).withEndZone(4).withPrice(200).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(3).withEndZone(3).withPrice(200).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(4).withEndZone(3).withPrice(200).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(3).withEndZone(1).withPrice(280).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(3).withEndZone(2).withPrice(280).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(4).withEndZone(1).withPrice(300).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(4).withEndZone(2).withPrice(300).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(1).withEndZone(3).withPrice(280).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(2).withEndZone(3).withPrice(280).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(1).withEndZone(4).withPrice(300).build());
        TRAVELS_PRICING.add(Travel.TravelBuilder.aTravel().withStartZone(2).withEndZone(4).withPrice(300).build());
    }
}
