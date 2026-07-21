import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

class GoCounting {
    private final char[][] board;
    private final int width;
    private final int height;

    GoCounting(String boardString) {
        if (boardString == null || boardString.isEmpty()) {
            this.board = new char[0][0];
            this.width = 0;
            this.height = 0;
            return;
        }
        
        String[] lines = boardString.split("\n");
        this.height = lines.length;
        this.width = lines[0].length();
        this.board = new char[height][width];
        
        for (int r = 0; r < height; r++) {
            this.board[r] = lines[r].toCharArray();
        }
    }

    // Returns the Player who owns the territory enclosing the given coordinate
    Player getTerritoryOwner(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        
        if (board[y][x] != ' ') {
            return Player.NONE;
        }

        Set<Point> territory = new HashSet<>();
        Set<Player> borders = new HashSet<>();
        bfs(x, y, territory, borders);

        if (borders.size() == 1) {
            return borders.iterator().next();
        }
        return Player.NONE;
    }

    // Returns the complete set of Points making up the territory enclosing the coordinate
    Set<Point> getTerritory(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        Set<Point> territory = new HashSet<>();
        if (board[y][x] != ' ') {
            return territory; // Return empty set if stone is picked
        }

        Set<Player> borders = new HashSet<>();
        bfs(x, y, territory, borders);
        return territory;
    }

    // Scans the whole board and groups all territories by player
    Map<Player, Set<Point>> getTerritories() {
        Map<Player, Set<Point>> territoriesMap = new HashMap<>();
        territoriesMap.put(Player.BLACK, new HashSet<>());
        territoriesMap.put(Player.WHITE, new HashSet<>());
        territoriesMap.put(Player.NONE, new HashSet<>());

        boolean[][] visited = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[y][x] == ' ' && !visited[y][x]) {
                    Set<Point> territory = new HashSet<>();
                    Set<Player> borders = new HashSet<>();
                    
                    // Run a full BFS for unvisited empty spaces
                    Queue<Point> queue = new LinkedList<>();
                    Point start = new Point(x, y);
                    queue.add(start);
                    territory.add(start);
                    visited[y][x] = true;

                    int[] dx = {1, -1, 0, 0};
                    int[] dy = {0, 0, 1, -1};

                    while (!queue.isEmpty()) {
                        Point curr = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = curr.x + dx[i];
                            int ny = curr.y + dy[i];

                            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                                if (board[ny][nx] == ' ') {
                                    Point next = new Point(nx, ny);
                                    if (!visited[ny][nx]) {
                                        visited[ny][nx] = true;
                                        territory.add(next);
                                        queue.add(next);
                                    }
                                } else if (board[ny][nx] == 'B') {
                                    borders.add(Player.BLACK);
                                } else if (board[ny][nx] == 'W') {
                                    borders.add(Player.WHITE);
                                }
                            }
                        }
                    }

                    // Assign the collected region to the proper owner
                    Player owner = Player.NONE;
                    if (borders.size() == 1) {
                        owner = borders.iterator().next();
                    }
                    territoriesMap.get(owner).addAll(territory);
                }
            }
        }

        return territoriesMap;
    }

    // Helper BFS to explore a territory starting from (startX, startY)
    private void bfs(int startX, int startY, Set<Point> territory, Set<Player> borders) {
        boolean[][] visited = new boolean[height][width];
        Queue<Point> queue = new LinkedList<>();
        
        Point start = new Point(startX, startY);
        queue.add(start);
        territory.add(start);
        visited[startY][startX] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (board[ny][nx] == ' ') {
                        Point next = new Point(nx, ny);
                        if (!visited[ny][nx]) {
                            visited[ny][nx] = true;
                            territory.add(next);
                            queue.add(next);
                        }
                    } else if (board[ny][nx] == 'B') {
                        borders.add(Player.BLACK);
                    } else if (board[ny][nx] == 'W') {
                        borders.add(Player.WHITE);
                    }
                }
            }
        }
    }
}