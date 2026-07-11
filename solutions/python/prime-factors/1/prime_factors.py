def factors(n):
    result = []
    divisor = 2

    while n > 1:
        # While divisor divides n cleanly, keep dividing
        while n % divisor == 0:
            result.append(divisor)
            n //= divisor

        divisor += 1

        # Optimization: no need to check beyond sqrt(n)
        if divisor * divisor > n and n > 1:
            result.append(n)
            break

    return result
