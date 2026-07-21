class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean inList = false;

        for (String line : lines) {
            line = parseInlineMarkdown(line);

            if (isHeader(line)) {
                if (inList) {
                    result.append("</ul>");
                    inList = false;
                }
                result.append(parseHeader(line));
            } else if (isListItem(line)) {
                if (!inList) {
                    result.append("<ul>");
                    inList = true;
                }
                result.append(parseListItem(line));
            } else {
                if (inList) {
                    result.append("</ul>");
                    inList = false;
                }
                result.append(parseParagraph(line));
            }
        }

        if (inList) {
            result.append("</ul>");
        }

        return result.toString();
    }

    private String parseInlineMarkdown(String line) {
        return line.replaceAll("__(.+?)__", "<strong>$1</strong>")
                   .replaceAll("_(.+?)_", "<em>$1</em>");
    }

    private boolean isHeader(String line) {
        if (!line.startsWith("#")) {
            return false;
        }
        int count = 0;
        while (count < line.length() && line.charAt(count) == '#') {
            count++;
        }
        return count <= 6; // Fixed: h7 and above becomes a paragraph
    }

    private String parseHeader(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == '#') {
            count++;
        }
        String content = line.substring(count).trim();
        return String.format("<h%d>%s</h%d>", count, content, count);
    }

    private boolean isListItem(String line) {
        return line.startsWith("* ");
    }

    private String parseListItem(String line) {
        String content = line.substring(2).trim();
        return "<li>" + content + "</li>";
    }

    private String parseParagraph(String line) {
        return "<p>" + line.trim() + "</p>";
    }
}