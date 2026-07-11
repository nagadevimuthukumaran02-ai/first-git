import java.util.ArrayList;
import java.util.List;

class KindergartenGarden {

    private static final String[] STUDENTS = {
        "Alice", "Bob", "Charlie", "David",
        "Eve", "Fred", "Ginny", "Harriet",
        "Ileana", "Joseph", "Kincaid", "Larry"
    };

    private final String[] rows;

    KindergartenGarden(String diagram) {
        rows = diagram.split("\n");
    }

    List<Plant> getPlantsOfStudent(String student) {
        int index = 0;

        while (!STUDENTS[index].equals(student)) {
            index++;
        }

        int start = index * 2;

        List<Plant> plants = new ArrayList<>();

        plants.add(toPlant(rows[0].charAt(start)));
        plants.add(toPlant(rows[0].charAt(start + 1)));
        plants.add(toPlant(rows[1].charAt(start)));
        plants.add(toPlant(rows[1].charAt(start + 1)));

        return plants;
    }

    private Plant toPlant(char c) {
        switch (c) {
            case 'G':
                return Plant.GRASS;
            case 'C':
                return Plant.CLOVER;
            case 'R':
                return Plant.RADISHES;
            case 'V':
                return Plant.VIOLETS;
            default:
                throw new IllegalArgumentException();
        }
    }
}