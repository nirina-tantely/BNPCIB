package org.bnpcib.model;

import org.bnpcib.exception.TechnicalException;

import java.util.Objects;

public class Tap implements ModelDto {
    private long unixTimestamp;
    private Integer customerId;
    private String station;

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getStation() {
        return station;
    }

    public Tap validate() {
        if (null == getCustomerId() || null == getStation() || getUnixTimestamp() < 1) {
            String errMsg = "Input data of tap is not correct: customerId or station name or time of tap cannot be null! => " + toString();
            throw new TechnicalException(errMsg);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Tap{" +
                "unixTimestamp=" + unixTimestamp +
                ", customerId=" + customerId +
                ", station='" + station + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tap tap = (Tap) o;
        return unixTimestamp == tap.unixTimestamp && customerId == tap.customerId && Objects.equals(station, tap.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unixTimestamp, customerId, station);
    }

    public static final class TapBuilder {
        private long unixTimestamp;
        private int customerId;
        private String station;

        public TapBuilder() {
        }

        public TapBuilder(Tap other) {
            this.unixTimestamp = other.unixTimestamp;
            this.customerId = other.customerId;
            this.station = other.station;
        }

        public static TapBuilder aTap() {
            return new TapBuilder();
        }

        public TapBuilder withUnixTimestamp(long unixTimestamp) {
            this.unixTimestamp = unixTimestamp;
            return this;
        }

        public TapBuilder withCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public TapBuilder withStation(String station) {
            this.station = station;
            return this;
        }

        public Tap build() {
            Tap tap = new Tap();
            tap.unixTimestamp = this.unixTimestamp;
            tap.station = this.station;
            tap.customerId = this.customerId;
            return tap;
        }
    }
}
