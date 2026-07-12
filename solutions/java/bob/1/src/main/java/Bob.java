class Bob {
    String hey(String input) {
        String trimmed = input.trim();

        boolean isSilence = trimmed.isEmpty();
        boolean isQuestion = trimmed.endsWith("?");
        boolean isYelling = trimmed.equals(trimmed.toUpperCase()) && !trimmed.equals(trimmed.toLowerCase());

        if (isSilence) {
            return "Fine. Be that way!";
        }
        if (isYelling && isQuestion) {
            return "Calm down, I know what I'm doing!";
        }
        if (isYelling) {
            return "Whoa, chill out!";
        }
        if (isQuestion) {
            return "Sure.";
        }
        return "Whatever.";
    }
}