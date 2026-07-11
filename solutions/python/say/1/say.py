ONES = {
    0: "zero", 1: "one", 2: "two", 3: "three", 4: "four",
    5: "five", 6: "six", 7: "seven", 8: "eight", 9: "nine",
    10: "ten", 11: "eleven", 12: "twelve", 13: "thirteen",
    14: "fourteen", 15: "fifteen", 16: "sixteen",
    17: "seventeen", 18: "eighteen", 19: "nineteen",
}

TENS = {
    20: "twenty", 30: "thirty", 40: "forty", 50: "fifty",
    60: "sixty", 70: "seventy", 80: "eighty", 90: "ninety",
}

SCALES = [
    (1_000_000_000, "billion"),
    (1_000_000, "million"),
    (1_000, "thousand"),
    (1, "")
]


def say_under_1000(n):
    """Convert a number < 1000 into words."""
    words = []

    # Hundreds
    if n >= 100:
        words.append(ONES[n // 100] + " hundred")
        n %= 100
        if n == 0:
            return " ".join(words)

    # 0–19
    if n < 20:
        if n != 0:
            words.append(ONES[n])
        return " ".join(words)

    # 20–99
    tens_part = (n // 10) * 10
    ones_part = n % 10

    if ones_part == 0:
        words.append(TENS[tens_part])
    else:
        words.append(TENS[tens_part] + "-" + ONES[ones_part])

    return " ".join(words)


def say(number):
    """Convert number to English words."""
    if number < 0 or number > 999_999_999_999:
        raise ValueError("input out of range")

    if number == 0:
        return "zero"

    result = []

    for scale_value, scale_name in SCALES:
        if number >= scale_value:
            chunk = number // scale_value
            number %= scale_value
            result.append(say_under_1000(chunk))
            if scale_name:
                result.append(scale_name)

    return " ".join(result)
