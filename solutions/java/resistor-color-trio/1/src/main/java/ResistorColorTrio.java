public class ResistorColorTrio {

    private static final String[] COLORS = {
        "black",
        "brown",
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "violet",
        "grey",
        "white"
    };

    public String label(String[] colors) {
        int first = colorCode(colors[0]);
        int second = colorCode(colors[1]);
        int multiplier = colorCode(colors[2]);

        long value = (first * 10L + second);

        for (int i = 0; i < multiplier; i++) {
            value *= 10;
        }

        if (value >= 1_000_000_000) {
            return (value / 1_000_000_000) + " gigaohms";
        } else if (value >= 1_000_000) {
            return (value / 1_000_000) + " megaohms";
        } else if (value >= 1_000) {
            return (value / 1_000) + " kiloohms";
        } else {
            return value + " ohms";
        }
    }

    private int colorCode(String color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return i;
            }
        }
        return -1;
    }
}