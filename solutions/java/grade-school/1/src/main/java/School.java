import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class School {

    // Keeps grades sorted (1, 2, 3...) and their list of students
    private final Map<Integer, List<String>> gradeRoster = new TreeMap<>();
    
    // Keeps track of all enrolled students globally to prevent double registration
    private final Set<String> registeredStudents = new HashSet<>();

    boolean add(String student, int grade) {
        // If the student is already in the school, reject the addition
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);
        
        // Add student to their grade and keep that grade's list sorted alphabetically
        gradeRoster.computeIfAbsent(grade, k -> new ArrayList<>()).add(student);
        Collections.sort(gradeRoster.get(grade));
        return true;
    }

    List<String> roster() {
        List<String> fullRoster = new ArrayList<>();
        // Since gradeRoster is a TreeMap, iterating values naturally processes grades in ascending order
        for (List<String> students : gradeRoster.values()) {
            fullRoster.addAll(students);
        }
        return Collections.unmodifiableList(fullRoster);
    }

    List<String> grade(int grade) {
        List<String> students = gradeRoster.get(grade);
        if (students == null) {
            return Collections.emptyList();
        }
        // Return a read-only copy of the list to protect internal state
        return Collections.unmodifiableList(new ArrayList<>(students));
    }
}