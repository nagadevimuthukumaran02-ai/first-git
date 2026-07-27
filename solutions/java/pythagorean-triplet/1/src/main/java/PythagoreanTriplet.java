import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class PythagoreanTriplet {

    private final int a;
    private final int b;
    private final int c;

    PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PythagoreanTriplet that = (PythagoreanTriplet) o;
        return a == that.a && b == that.b && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    static class TripletListBuilder {
        private int sum;
        private Integer maxFactor;
        private Integer minFactor;

        TripletListBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        TripletListBuilder withFactorsGreaterThanOrEqualTo(int minFactor) {
            this.minFactor = minFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> triplets = new ArrayList<>();
            int min = (minFactor != null) ? minFactor : 1;
            int max = (maxFactor != null) ? maxFactor : sum;

            // Since a < b < c and a + b + c = N, 'a' cannot exceed sum / 3
            int maxA = sum / 3;

            for (int a = min; a <= maxA; a++) {
                // Derived from a^2 + b^2 = c^2 and a + b + c = sum:
                // b = (sum^2 - 2 * sum * a) / (2 * (sum - a))
                int numerator = sum * sum - 2 * sum * a;
                int denominator = 2 * (sum - a);

                if (numerator % denominator == 0) {
                    int b = numerator / denominator;
                    int c = sum - a - b;

                    if (b > a && b <= max && c <= max) {
                        triplets.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }

            return triplets;
        }
    }
}