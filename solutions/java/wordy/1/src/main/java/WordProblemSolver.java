import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordProblemSolver {

    int solve(final String wordProblem) {

        if (!wordProblem.startsWith("What is ") || !wordProblem.endsWith("?")) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        String expression = wordProblem.substring(8, wordProblem.length() - 1).trim();

        Pattern numberPattern = Pattern.compile("^-?\\d+");
        Matcher matcher = numberPattern.matcher(expression);

        if (!matcher.find()) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        int result = Integer.parseInt(matcher.group());
        int index = matcher.end();

        while (index < expression.length()) {

            while (index < expression.length() && expression.charAt(index) == ' ') {
                index++;
            }

            String operator;

            if (expression.startsWith("plus", index)) {
                operator = "plus";
                index += 4;
            } else if (expression.startsWith("minus", index)) {
                operator = "minus";
                index += 5;
            } else if (expression.startsWith("multiplied by", index)) {
                operator = "multiplied";
                index += 13;
            } else if (expression.startsWith("divided by", index)) {
                operator = "divided";
                index += 10;
            } else {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

            while (index < expression.length() && expression.charAt(index) == ' ') {
                index++;
            }

            matcher = numberPattern.matcher(expression.substring(index));
            if (!matcher.find() || matcher.start() != 0) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

            int number = Integer.parseInt(matcher.group());

            switch (operator) {
                case "plus":
                    result += number;
                    break;
                case "minus":
                    result -= number;
                    break;
                case "multiplied":
                    result *= number;
                    break;
                case "divided":
                    result /= number;
                    break;
            }

            index += matcher.end();
        }

        return result;
    }
}