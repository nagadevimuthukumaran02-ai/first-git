import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Tournament {
    private final Map<String, TeamStatistics> table = new HashMap<>();

    void applyResults(String resultString) {
        if (resultString == null || resultString.trim().isEmpty()) {
            return;
        }

        String[] lines = resultString.split("\n");
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length != 3) continue;

            String team1 = parts[0];
            String team2 = parts[1];
            String outcome = parts[2];

            // Ensure teams exist in our map
            table.putIfAbsent(team1, new TeamStatistics(team1));
            table.putIfAbsent(team2, new TeamStatistics(team2));

            switch (outcome) {
                case "win":
                    table.get(team1).addWin();
                    table.get(team2).addLoss();
                    break;
                case "loss":
                    table.get(team1).addLoss();
                    table.get(team2).addWin();
                    break;
                case "draw":
                    table.get(team1).addDraw();
                    table.get(team2).addDraw();
                    break;
            }
        }
    }

    String printTable() {
        StringBuilder sb = new StringBuilder();
        // Header line formatted to match strict spacing requirements
        sb.append(String.format("%-30s | MP |  W |  D |  L |  P\n", "Team"));

        List<TeamStatistics> teams = new ArrayList<>(table.values());

        // Sort: Points descending, then Name alphabetically ascending
        Collections.sort(teams, (t1, t2) -> {
            if (t1.getPoints() != t2.getPoints()) {
                return Integer.compare(t2.getPoints(), t1.getPoints());
            }
            return t1.getName().compareTo(t2.getName());
        });

        // Append each formatted row
        for (TeamStatistics team : teams) {
            sb.append(String.format("%-30s | %2d | %2d | %2d | %2d | %2d\n",
                    team.getName(),
                    team.getMatchesPlayed(),
                    team.getWins(),
                    team.getDraws(),
                    team.getLosses(),
                    team.getPoints()));
        }

        return sb.toString();
    }

    // Helper class to encapsulate a single team's score tracking
    private static class TeamStatistics {
        private final String name;
        private int wins = 0;
        private int draws = 0;
        private int losses = 0;

        public TeamStatistics(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        public int getWins() { return wins; }
        public int getDraws() { return draws; }
        public int getLosses() { return losses; }

        public int getMatchesPlayed() {
            return wins + draws + losses;
        }

        public int getPoints() {
            return (wins * 3) + draws;
        }

        public void addWin() { wins++; }
        public void addDraw() { draws++; }
        public void addLoss() { losses++; }
    }
}