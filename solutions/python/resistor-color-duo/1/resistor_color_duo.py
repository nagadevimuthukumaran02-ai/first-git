# resistor_color_duo.py

# Mapping of color to digit
COLOR_CODES = {
    "black": 0,
    "brown": 1,
    "red": 2,
    "orange": 3,
    "yellow": 4,
    "green": 5,
    "blue": 6,
    "violet": 7,
    "grey": 8,
    "white": 9
}

def value(colors):
    """
    Given a list of color names, return the two-digit resistor value
    corresponding to the first two colors.
    """
    first_two = colors[:2]
    digits = [str(COLOR_CODES[color.lower()]) for color in first_two]
    return int("".join(digits))
