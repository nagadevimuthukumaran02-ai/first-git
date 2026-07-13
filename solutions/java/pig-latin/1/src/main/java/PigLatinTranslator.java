class PigLatinTranslator {

    public String translate(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            result.append(translateWord(words[i]));
        }

        return result.toString();
    }

    private String translateWord(String word) {

        // Rule 1: starts with vowel, "xr", or "yt"
        if (word.matches("^(?:[aeiou].*|xr.*|yt.*)")) {
            return word + "ay";
        }

        // Rule 3: consonants followed by "qu"
        if (word.matches("^([^aeiou]*qu).*")) {
            int index = word.indexOf("qu") + 2;
            return word.substring(index) + word.substring(0, index) + "ay";
        }

        // Rule 4: consonants followed by 'y'
        if (word.matches("^[^aeiou]+y.*")) {
            int index = word.indexOf('y');
            return word.substring(index) + word.substring(0, index) + "ay";
        }

        // Rule 2: leading consonant(s)
        int index = 0;
        while (index < word.length()
                && "aeiou".indexOf(word.charAt(index)) == -1) {
            index++;
        }

        return word.substring(index) + word.substring(0, index) + "ay";
    }
}