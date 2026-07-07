class SqueakyClean {

    static String clean(String identifier) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (char ch : identifier.toCharArray()) {

            // Replace spaces with underscores
            if (ch == ' ') {
                result.append('_');
                capitalizeNext = false;
                continue;
            }

            // Convert kebab-case to camelCase
            if (ch == '-') {
                capitalizeNext = true;
                continue;
            }

            // Convert leetspeak
            switch (ch) {
                case '4':
                    ch = 'a';
                    break;
                case '3':
                    ch = 'e';
                    break;
                case '0':
                    ch = 'o';
                    break;
                case '1':
                    ch = 'l';
                    break;
                case '7':
                    ch = 't';
                    break;
            }

            // Keep only letters and underscores
            if (Character.isLetter(ch) || ch == '_') {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(ch));
                    capitalizeNext = false;
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}