import java.math.BigInteger;

public class Grains {

    public BigInteger grainsOnSquare(final int square) {
        if (square < 1) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }

        if (square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }

        return BigInteger.ONE.shiftLeft(square - 1);
    }

    public BigInteger grainsOnBoard() {
        return BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE);
    }
}