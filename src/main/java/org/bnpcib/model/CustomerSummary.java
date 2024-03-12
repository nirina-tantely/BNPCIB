package org.bnpcib.model;

import java.util.List;
import java.util.Objects;

public class CustomerSummary implements ModelDto {
    private int customerId;
    private int totalCostInCents;
    private List<Trip> trips;

    public int getCustomerId() {
        return customerId;
    }

    public int getTotalCostInCents() {
        return totalCostInCents;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSummary that = (CustomerSummary) o;
        return customerId == that.customerId && totalCostInCents == that.totalCostInCents && Objects.equals(trips, that.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, totalCostInCents, trips);
    }

    @Override
    public String toString() {
        return "CustomerSummary{" +
                "customerId=" + customerId +
                ", totalCostInCents=" + totalCostInCents +
                ", trips=" + trips +
                '}';
    }

    public static final class CustomerSummaryBuilder {
        private int customerId;
        private int totalCostInCents;
        private List<Trip> trips;

        public CustomerSummaryBuilder() {
        }

        public CustomerSummaryBuilder(CustomerSummary other) {
            this.customerId = other.customerId;
            this.totalCostInCents = other.totalCostInCents;
            this.trips = other.trips;
        }

        public static CustomerSummaryBuilder aCustomerSummary() {
            return new CustomerSummaryBuilder();
        }

        public CustomerSummaryBuilder withCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerSummaryBuilder withTotalCostInCents(int totalCostInCents) {
            this.totalCostInCents = totalCostInCents;
            return this;
        }

        public CustomerSummaryBuilder withTrips(List<Trip> trips) {
            this.trips = trips;
            return this;
        }

        public CustomerSummary build() {
            CustomerSummary customerSummary = new CustomerSummary();
            customerSummary.totalCostInCents = this.totalCostInCents;
            customerSummary.customerId = this.customerId;
            customerSummary.trips = this.trips;
            return customerSummary;
        }
    }
}
