def score(x, y):
    # Calculate distance from the center
    distance = (x**2 + y**2) ** 0.5

    # Check scoring zones
    if distance <= 1:
        return 10
    elif distance <= 5:
        return 5
    elif distance <= 10:
        return 1
    else:
        return 0
