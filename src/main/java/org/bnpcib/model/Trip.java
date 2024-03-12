package org.bnpcib.model;

import java.util.Objects;

public class Trip implements ModelDto {
    private String stationStart;
    private String stationEnd;
    private long startedJourneyAt;
    private int costInCents;
    private int zoneFrom;
    private int zoneTo;

    public String getStationStart() {
        return stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public long getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "stationStart='" + stationStart + '\'' +
                ", stationEnd='" + stationEnd + '\'' +
                ", startedJourneyAt=" + startedJourneyAt +
                ", costInCents=" + costInCents +
                ", zoneFrom=" + zoneFrom +
                ", zoneTo=" + zoneTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return startedJourneyAt == trip.startedJourneyAt && costInCents == trip.costInCents && zoneFrom == trip.zoneFrom && zoneTo == trip.zoneTo && Objects.equals(stationStart, trip.stationStart) && Objects.equals(stationEnd, trip.stationEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationStart, stationEnd, startedJourneyAt, costInCents, zoneFrom, zoneTo);
    }


    public static final class TripBuilder {
        private String stationStart;
        private String stationEnd;
        private long startedJourneyAt;
        private int costInCents;
        private int zoneFrom;
        private int zoneTo;

        public TripBuilder() {
        }

        public TripBuilder(Trip other) {
            this.stationStart = other.stationStart;
            this.stationEnd = other.stationEnd;
            this.startedJourneyAt = other.startedJourneyAt;
            this.costInCents = other.costInCents;
            this.zoneFrom = other.zoneFrom;
            this.zoneTo = other.zoneTo;
        }

        public static TripBuilder aTrip() {
            return new TripBuilder();
        }

        public TripBuilder withStationStart(String stationStart) {
            this.stationStart = stationStart;
            return this;
        }

        public TripBuilder withStationEnd(String stationEnd) {
            this.stationEnd = stationEnd;
            return this;
        }

        public TripBuilder withStartedJourneyAt(long startedJourneyAt) {
            this.startedJourneyAt = startedJourneyAt;
            return this;
        }

        public TripBuilder withCostInCents(int costInCents) {
            this.costInCents = costInCents;
            return this;
        }

        public TripBuilder withZoneFrom(int zoneFrom) {
            this.zoneFrom = zoneFrom;
            return this;
        }

        public TripBuilder withZoneTo(int zoneTo) {
            this.zoneTo = zoneTo;
            return this;
        }

        public Trip build() {
            Trip trip = new Trip();
            trip.stationStart = this.stationStart;
            trip.stationEnd = this.stationEnd;
            trip.costInCents = this.costInCents;
            trip.zoneTo = this.zoneTo;
            trip.zoneFrom = this.zoneFrom;
            trip.startedJourneyAt = this.startedJourneyAt;
            return trip;
        }
    }
}
