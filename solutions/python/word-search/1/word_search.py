class Point:
    def __init__(self, x, y):
        # x = column (0-based), y = row (0-based)
        self.x = x
        self.y = y
    def __eq__(self, other):
        if not isinstance(other, Point):
            return NotImplemented
        return self.x == other.x and self.y == other.y
    def __repr__(self):
        return f"Point({self.x}, {self.y})"
# search directions: right, left, down, up, down-right, up-right, down-left, up-left
DIRECTIONS = [
    (1, 0), (-1, 0),
    (0, 1), (0, -1),
    (1, 1), (1, -1),
    (-1, 1), (-1, -1),
]
class WordSearch:
    def __init__(self, puzzle):
        """puzzle: list of strings (each string is a row)."""
        # normalize to list of lists for indexing
        self.grid = [list(row) for row in puzzle]
        self.rows = len(self.grid)
        self.cols = len(self.grid[0]) if self.rows > 0 else 0
    def _in_bounds(self, r, c):
        return 0 <= r < self.rows and 0 <= c < self.cols
    def _match_from(self, word, r, c, dr, dc):
        """Attempt match from (r,c) (row, col) stepping (dr,dc).
        If match, return (start_point, end_point) with zero-based Points.
        Otherwise return None.
        """
        for i, ch in enumerate(word):
            nr = r + dr * i
            nc = c + dc * i
            if not self._in_bounds(nr, nc) or self.grid[nr][nc] != ch:
                return None
        # end coordinates
        end_r = r + dr * (len(word) - 1)
        end_c = c + dc * (len(word) - 1)
        # convert to Points (x=col, y=row), zero-based
        return (Point(c, r), Point(end_c, end_r))
    def search(self, word):
        """Return (start_point, end_point) or None for first found occurrence."""
        if not word:
            return None
        for r in range(self.rows):
            for c in range(self.cols):
                if self.grid[r][c] != word[0]:
                    continue
                for dc, dr in [(d[0], d[1]) for d in DIRECTIONS]:
                    # Note: DIRECTIONS stored as (dx,dy) but _match_from expects (dr,dc)
                    # We'll pass dr=dr, dc=dc with correct ordering below
                    # (we'll map accordingly)
                    pass
                # Instead of the above confusion, iterate directions properly:
                for dx, dy in DIRECTIONS:
                    # dx = change in column, dy = change in row
                    result = self._match_from(word, r, c, dy, dx)
                    if result:
                        return result
        return None