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
    "white": 9,
}

# Metric prefixes in descending order
PREFIXES = [
    (1_000_000_000, "gigaohms"),
    (1_000_000, "megaohms"),
    (1_000, "kiloohms"),
    (1, "ohms"),
]


def label(colors):
    # First two bands form the base value
    first = COLOR_CODES[colors[0]]
    second = COLOR_CODES[colors[1]]
    multiplier = COLOR_CODES[colors[2]]

    value = (first * 10 + second) * (10 ** multiplier)

    # Special case for ZERO ohms
    if value == 0:
        return "0 ohms"

    # Choose correct metric prefix
    for factor, prefix in PREFIXES:
        if value >= factor:
            amount = value // factor
            return f"{amount} {prefix}"
