class Acronym {

    private final String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String text = phrase.replace("-", " ");
        text = text.replaceAll("[^a-zA-Z0-9 ]", "");

        StringBuilder acronym = new StringBuilder();

        for (String word : text.split("\\s+")) {
            if (!word.isEmpty()) {
                acronym.append(Character.toUpperCase(word.charAt(0)));
            }
        }

        return acronym.toString();
    }
}