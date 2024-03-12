package org.bnpcib.model;

import java.util.List;
import java.util.Objects;

public class InputData implements ModelDto {
    private List<Tap> taps;

    public List<Tap> getTaps() {
        return taps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputData inputData = (InputData) o;
        return Objects.equals(taps, inputData.taps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taps);
    }

    public static final class InputDataBuilder {
        private List<Tap> taps;

        public InputDataBuilder() {
        }

        public InputDataBuilder(InputData other) {
            this.taps = other.taps;
        }

        public static InputDataBuilder anInputData() {
            return new InputDataBuilder();
        }

        public InputDataBuilder withTaps(List<Tap> taps) {
            this.taps = taps;
            return this;
        }

        public InputData build() {
            InputData inputData = new InputData();
            inputData.taps = this.taps;
            return inputData;
        }
    }
}
