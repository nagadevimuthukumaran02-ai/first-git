def square(number):
    """
    Return the number of grains on a given square.
    Square 1 has 1 grain, square 2 has 2, square 3 has 4, etc.
    """
    if number < 1 or number > 64:
        raise ValueError("square must be between 1 and 64")
    return 2 ** (number - 1)


def total():
    """
    Return the total number of grains on the chessboard (all 64 squares).
    """
    return (2 ** 64) - 1