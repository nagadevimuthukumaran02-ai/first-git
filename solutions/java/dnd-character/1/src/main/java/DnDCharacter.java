import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class DnDCharacter {

    private static final Random RANDOM = new Random();

    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;
    private final int hitpoints;

    DnDCharacter() {
        strength = ability();
        dexterity = ability();
        constitution = ability();
        intelligence = ability();
        wisdom = ability();
        charisma = ability();
        hitpoints = 10 + modifier(constitution);
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return hitpoints;
    }

    static int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.0);
    }

    List<Integer> rollDice() {
        List<Integer> rolls = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            rolls.add(RANDOM.nextInt(6) + 1);
        }

        return rolls;
    }

    int ability(List<Integer> scores) {
        List<Integer> dice = new ArrayList<>(scores);
        Collections.sort(dice);

        return dice.get(1) + dice.get(2) + dice.get(3);
    }

    int ability() {
        return ability(rollDice());
    }
}