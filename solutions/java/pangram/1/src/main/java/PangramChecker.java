class PangramChecker {

    boolean isPangram(String sentence) {
        boolean[] letters = new boolean[26];
        int count = 0;

        sentence = sentence.toLowerCase();

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                int index = ch - 'a';

                if (!letters[index]) {
                    letters[index] = true;
                    count++;
                }
            }
        }

        return count == 26;
    }
}