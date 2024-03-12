package org.bnpcib.model;

import java.util.List;
import java.util.Objects;

public class OutputData implements ModelDto {
    private List<CustomerSummary> customerSummaries;

    public List<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputData that = (OutputData) o;
        return Objects.equals(customerSummaries, that.customerSummaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerSummaries);
    }


    public static final class OutputDataBuilder {
        private List<CustomerSummary> customerSummaries;

        public OutputDataBuilder() {
        }

        public OutputDataBuilder(OutputData other) {
            this.customerSummaries = other.customerSummaries;
        }

        public static OutputDataBuilder anOutputData() {
            return new OutputDataBuilder();
        }

        public OutputDataBuilder withCustomerSummaries(List<CustomerSummary> customerSummaries) {
            this.customerSummaries = customerSummaries;
            return this;
        }

        public OutputData build() {
            OutputData outputData = new OutputData();
            outputData.customerSummaries = this.customerSummaries;
            return outputData;
        }
    }
}
