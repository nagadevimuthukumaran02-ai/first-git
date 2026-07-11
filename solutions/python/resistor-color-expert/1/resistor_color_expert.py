def resistor_label(colors):
    digit_values = {
        "black": 0, "brown": 1, "red": 2, "orange": 3, "yellow": 4,
        "green": 5, "blue": 6, "violet": 7, "grey": 8, "white": 9
    }

    tolerance_values = {
        "grey": "±0.05%", "violet": "±0.1%", "blue": "±0.25%", "green": "±0.5%",
        "brown": "±1%", "red": "±2%", "gold": "±5%", "silver": "±10%"
    }

    # 1-band resistor
    if len(colors) == 1:
        return "0 ohms"

    # 4-band resistor
    if len(colors) == 4:
        d1 = digit_values[colors[0]]
        d2 = digit_values[colors[1]]
        multiplier = 10 ** digit_values[colors[2]]
        tolerance = tolerance_values[colors[3]]
        value = (d1 * 10 + d2) * multiplier

    # 5-band resistor
    elif len(colors) == 5:
        d1 = digit_values[colors[0]]
        d2 = digit_values[colors[1]]
        d3 = digit_values[colors[2]]
        multiplier = 10 ** digit_values[colors[3]]
        tolerance = tolerance_values[colors[4]]
        value = (d1 * 100 + d2 * 10 + d3) * multiplier

    # Format output
    if value >= 1_000_000:
        value_str = f"{value / 1_000_000:g} megaohms"
    elif value >= 1_000:
        value_str = f"{value / 1_000:g} kiloohms"
    else:
        value_str = f"{value} ohms"

    return f"{value_str} {tolerance}"
