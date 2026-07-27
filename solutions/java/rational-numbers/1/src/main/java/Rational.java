import java.util.Objects;

class Rational {

    private final int numerator;
    private final int denominator;

    Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        // Simplify signs (ensure denominator is positive)
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        // Reduce to lowest terms using Greatest Common Divisor (GCD)
        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    // Addition: (a1 * b2 + a2 * b1) / (b1 * b2)
    Rational add(Rational other) {
        int newNum = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    // Subtraction: (a1 * b2 - a2 * b1) / (b1 * b2)
    Rational subtract(Rational other) {
        int newNum = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    // Multiplication: (a1 * a2) / (b1 * b2)
    Rational multiply(Rational other) {
        int newNum = this.numerator * other.numerator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    // Division: (a1 * b2) / (a2 * b1)
    Rational divide(Rational other) {
        int newNum = this.numerator * other.denominator;
        int newDen = this.denominator * other.numerator;
        return new Rational(newNum, newDen);
    }

    // Absolute Value: |a| / |b|
    Rational abs() {
        return new Rational(Math.abs(this.numerator), Math.abs(this.denominator));
    }

    // Exponentiation to an integer power
    Rational pow(int power) {
        if (power >= 0) {
            return new Rational(
                (int) Math.pow(this.numerator, power),
                (int) Math.pow(this.denominator, power)
            );
        } else {
            int m = Math.abs(power);
            return new Rational(
                (int) Math.pow(this.denominator, m),
                (int) Math.pow(this.numerator, m)
            );
        }
    }

    // Exponentiation to a real power: (a^x) / (b^x)
    double pow(double power) {
        return Math.pow(this.numerator, power) / Math.pow(this.denominator, power);
    }

    // Exponentiation of a real base x to a rational power (a/b): root(x^a, b)
    double exp(double base) {
        return Math.pow(base, (double) this.numerator / this.denominator);
    }

    // Greatest Common Divisor calculation using Euclidean algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator && denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
}