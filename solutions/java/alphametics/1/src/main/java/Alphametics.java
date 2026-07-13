import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Alphametics {

    private final List<String> addends;
    private final String result;
    private final int width;
    private final Set<Character> leadingLetters = new HashSet<>();
    private final Map<Character, Integer> assignment = new HashMap<>();
    private final boolean[] usedDigit = new boolean[10];

    Alphametics(String userInput) {
        String[] sides = userInput.split("==");
        String left = sides[0].trim();
        String right = sides[1].trim();

        addends = new ArrayList<>();
        for (String w : left.split("\\+")) {
            addends.add(w.trim());
        }
        result = right;
        width = result.length();

        for (String w : addends) {
            if (w.length() > 1) {
                leadingLetters.add(w.charAt(0));
            }
        }
        if (result.length() > 1) {
            leadingLetters.add(result.charAt(0));
        }
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        Set<Character> allLetters = new HashSet<>();
        for (String w : addends) {
            for (char c : w.toCharArray()) {
                allLetters.add(c);
            }
        }
        for (char c : result.toCharArray()) {
            allLetters.add(c);
        }

        int maxAddendLength = 0;
        for (String w : addends) {
            maxAddendLength = Math.max(maxAddendLength, w.length());
        }

        if (allLetters.size() > 10 || maxAddendLength > width) {
            throw new UnsolvablePuzzleException();
        }

        if (solveColumn(0, 0)) {
            return new HashMap<>(assignment);
        }
        throw new UnsolvablePuzzleException();
    }

    private boolean solveColumn(int col, int carry) {
        if (col == width) {
            return carry == 0;
        }

        int pos = width - 1 - col;
        char resultChar = result.charAt(pos);

        List<Character> addendLetters = new ArrayList<>();
        for (String w : addends) {
            int wpos = w.length() - 1 - col;
            if (wpos >= 0) {
                char c = w.charAt(wpos);
                if (!addendLetters.contains(c)) {
                    addendLetters.add(c);
                }
            }
        }

        List<Character> unassigned = new ArrayList<>();
        for (char c : addendLetters) {
            if (!assignment.containsKey(c)) {
                unassigned.add(c);
            }
        }

        return assignAddendLetters(unassigned, 0, col, carry, pos, resultChar);
    }

    private boolean assignAddendLetters(List<Character> letters, int idx, int col,
                                         int carry, int pos, char resultChar) {
        if (idx == letters.size()) {
            int sum = carry;
            for (String w : addends) {
                int wpos = w.length() - 1 - col;
                if (wpos >= 0) {
                    sum += assignment.get(w.charAt(wpos));
                }
            }
            int digit = sum % 10;
            int newCarry = sum / 10;

            Integer existing = assignment.get(resultChar);
            if (existing != null) {
                if (existing != digit) {
                    return false;
                }
                return solveColumn(col + 1, newCarry);
            } else {
                if (usedDigit[digit]) {
                    return false;
                }
                if (digit == 0 && leadingLetters.contains(resultChar)) {
                    return false;
                }
                assignment.put(resultChar, digit);
                usedDigit[digit] = true;
                if (solveColumn(col + 1, newCarry)) {
                    return true;
                }
                usedDigit[digit] = false;
                assignment.remove(resultChar);
                return false;
            }
        }

        char letter = letters.get(idx);
        for (int d = 0; d <= 9; d++) {
            if (usedDigit[d]) {
                continue;
            }
            if (d == 0 && leadingLetters.contains(letter)) {
                continue;
            }
            assignment.put(letter, d);
            usedDigit[d] = true;
            if (assignAddendLetters(letters, idx + 1, col, carry, pos, resultChar)) {
                return true;
            }
            usedDigit[d] = false;
            assignment.remove(letter);
        }
        return false;
    }
}
