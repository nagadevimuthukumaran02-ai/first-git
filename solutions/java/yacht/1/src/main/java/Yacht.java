import java.util.Arrays;

class Yacht {

    private final int[] dice;
    private final YachtCategory category;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.dice = dice;
        this.category = yachtCategory;
    }

    int score() {
        int[] count = new int[7];
        int sum = 0;

        for (int die : dice) {
            count[die]++;
            sum += die;
        }

        switch (category) {
            case ONES:
                return count[1];
            case TWOS:
                return count[2] * 2;
            case THREES:
                return count[3] * 3;
            case FOURS:
                return count[4] * 4;
            case FIVES:
                return count[5] * 5;
            case SIXES:
                return count[6] * 6;

            case CHOICE:
                return sum;

            case YACHT:
                for (int i = 1; i <= 6; i++) {
                    if (count[i] == 5) {
                        return 50;
                    }
                }
                return 0;

            case FULL_HOUSE:
                boolean hasThree = false;
                boolean hasTwo = false;
                for (int i = 1; i <= 6; i++) {
                    if (count[i] == 3) {
                        hasThree = true;
                    } else if (count[i] == 2) {
                        hasTwo = true;
                    }
                }
                return (hasThree && hasTwo) ? sum : 0;

            case FOUR_OF_A_KIND:
                for (int i = 1; i <= 6; i++) {
                    if (count[i] >= 4) {
                        return i * 4;
                    }
                }
                return 0;

            case LITTLE_STRAIGHT:
                return Arrays.equals(count,
                        new int[]{0, 1, 1, 1, 1, 1, 0}) ? 30 : 0;

            case BIG_STRAIGHT:
                return Arrays.equals(count,
                        new int[]{0, 0, 1, 1, 1, 1, 1}) ? 30 : 0;

            default:
                return 0;
        }
    }
}