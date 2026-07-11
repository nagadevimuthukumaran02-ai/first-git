def is_armstrong_number(number):
    """
    Return True if number is an Armstrong number, False otherwise.
    """
    digits = str(number)
    power = len(digits)

    total = sum(int(d) ** power for d in digits)

    return total == number
