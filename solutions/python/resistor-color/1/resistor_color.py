# List of resistor colors in order
COLORS = [
    "black",
    "brown",
    "red",
    "orange",
    "yellow",
    "green",
    "blue",
    "violet",
    "grey",
    "white",
]

def color_code(color: str) -> int:
    """Return the numeric value of a color band."""
    return COLORS.index(color)


def colors() -> list:
    """Return the list of all colors."""
    return COLORS
