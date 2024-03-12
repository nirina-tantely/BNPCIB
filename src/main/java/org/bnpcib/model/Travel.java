package org.bnpcib.model;

import java.util.Objects;

public class Travel {

    private int startZone;
    private int endZone;
    private int price;

    public int getStartZone() {
        return startZone;
    }

    public int getEndZone() {
        return endZone;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return startZone == travel.startZone && endZone == travel.endZone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startZone, endZone);
    }


    public static final class TravelBuilder {
        private int startZone;
        private int endZone;
        private int price;

        public TravelBuilder() {
        }

        public TravelBuilder(Travel other) {
            this.startZone = other.startZone;
            this.endZone = other.endZone;
            this.price = other.price;
        }

        public static TravelBuilder aTravel() {
            return new TravelBuilder();
        }

        public TravelBuilder withStartZone(int startZone) {
            this.startZone = startZone;
            return this;
        }

        public TravelBuilder withEndZone(int endZone) {
            this.endZone = endZone;
            return this;
        }

        public TravelBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Travel build() {
            Travel travel = new Travel();
            travel.startZone = this.startZone;
            travel.endZone = this.endZone;
            travel.price = this.price;
            return travel;
        }
    }
}
