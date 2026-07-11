def steps(number):
    """
    Return the number of steps required to reach 1
    using the Collatz Conjecture rules.
    """

    if number <= 0:
        raise ValueError("Only positive integers are allowed")

    count = 0

    while number != 1:
        if number % 2 == 0:
            number //= 2
        else:
            number = 3 * number + 1

        count += 1

    return count
