import java.util.ArrayList;
import java.util.List;

class BowlingGame {

    private final List<Integer> rolls = new ArrayList<>();
    private final List<Integer> tenthFrameRolls = new ArrayList<>();

    private int frameIndex = 1;      // current frame number, 1..10
    private int rollInFrame = 0;     // rolls recorded so far in current frame (frames 1-9)
    private int firstRollThisFrame = -1;
    private boolean gameOver = false;

    void roll(int pins) {
        if (gameOver) {
            throw new IllegalStateException("Cannot roll after game is over");
        }
        if (pins < 0) {
            throw new IllegalStateException("Negative roll is invalid");
        }
        if (pins > 10) {
            throw new IllegalStateException("Pin count exceeds pins on the lane");
        }

        if (frameIndex <= 9) {
            rollNormalFrame(pins);
        } else {
            rollTenthFrame(pins);
        }
    }

    private void rollNormalFrame(int pins) {
        if (rollInFrame == 0) {
            rolls.add(pins);
            if (pins == 10) {
                frameIndex++;
            } else {
                firstRollThisFrame = pins;
                rollInFrame = 1;
            }
        } else {
            if (firstRollThisFrame + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
            rolls.add(pins);
            frameIndex++;
            rollInFrame = 0;
            firstRollThisFrame = -1;
        }
    }

    private void rollTenthFrame(int pins) {
        int n = tenthFrameRolls.size();

        if (n == 1) {
            int r1 = tenthFrameRolls.get(0);
            if (r1 != 10 && r1 + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
        } else if (n == 2) {
            int r1 = tenthFrameRolls.get(0);
            int r2 = tenthFrameRolls.get(1);
            if (r1 == 10 && r2 != 10 && r2 + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
        }

        tenthFrameRolls.add(pins);
        rolls.add(pins);
        n = tenthFrameRolls.size();

        if (n == 2) {
            int r1 = tenthFrameRolls.get(0);
            int r2 = tenthFrameRolls.get(1);
            if (r1 != 10 && r1 + r2 < 10) {
                gameOver = true;
            }
        } else if (n == 3) {
            gameOver = true;
        }
    }

    int score() {
        if (!gameOver) {
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        }

        int total = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {
                total += 10 + rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                total += 10 + rolls.get(rollIndex + 2);
                rollIndex += 2;
            } else {
                total += rolls.get(rollIndex) + rolls.get(rollIndex + 1);
                rollIndex += 2;
            }
        }

        return total;
    }

    private boolean isStrike(int rollIndex) {
        return rolls.get(rollIndex) == 10;
    }

    private boolean isSpare(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10;
    }
}