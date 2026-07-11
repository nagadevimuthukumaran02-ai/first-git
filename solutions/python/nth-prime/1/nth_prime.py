def prime(n):
    """
    Return the nth prime number.
    Raise ValueError for n < 1.
    """
    if n < 1:
        raise ValueError("there is no zeroth prime")

    count = 0
    number = 1

    while count < n:
        number += 1
        if is_prime(number):
            count += 1

    return number


def is_prime(num):
    """
    Check whether num is a prime number.
    """
    if num < 2:
        return False

    i = 2
    while i * i <= num:
        if num % i == 0:
            return False
        i += 1

    return True
