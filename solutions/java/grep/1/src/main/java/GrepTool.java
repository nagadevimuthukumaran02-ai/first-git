import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class GrepTool {

    String grep(String pattern, List<String> flags, List<String> files) {
        boolean lineNumber = flags.contains("-n");
        boolean filenamesOnly = flags.contains("-l");
        boolean caseInsensitive = flags.contains("-i");
        boolean invert = flags.contains("-v");
        boolean entireLine = flags.contains("-x");

        boolean multipleFiles = files.size() > 1;
        List<String> result = new ArrayList<>();

        String searchPattern = caseInsensitive ? pattern.toLowerCase() : pattern;

        for (String file : files) {
            List<String> lines = readLines(file);
            boolean fileHasMatch = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String compareLine = caseInsensitive ? line.toLowerCase() : line;

                boolean matches = entireLine
                        ? compareLine.equals(searchPattern)
                        : compareLine.contains(searchPattern);

                if (invert) {
                    matches = !matches;
                }

                if (matches) {
                    fileHasMatch = true;

                    if (filenamesOnly) {
                        continue; // we only need to know the file matched; skip building the line
                    }

                    StringBuilder sb = new StringBuilder();
                    if (multipleFiles) {
                        sb.append(file).append(":");
                    }
                    if (lineNumber) {
                        sb.append(i + 1).append(":");
                    }
                    sb.append(line);
                    result.add(sb.toString());
                }
            }

            if (filenamesOnly && fileHasMatch) {
                result.add(file);
            }
        }

        return String.join("\n", result);
    }

    private List<String> readLines(String file) {
        try {
            return Files.readAllLines(Paths.get(file));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}