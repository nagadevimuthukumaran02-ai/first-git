public class ResistorColorDuo {

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

    public int value(String[] colors) {
        int first = colorCode(colors[0]);
        int second = colorCode(colors[1]);
        return first * 10 + second;
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