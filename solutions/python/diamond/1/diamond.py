def rows(letter):
    # Sequence from A to given letter
    size = ord(letter) - ord('A')
    
    result = []

    # Top half including the middle row
    for i in range(size + 1):
        ch = chr(ord('A') + i)
        if i == 0:
            row = " " * (size - i) + ch + " " * (size - i)
        else:
            row = (" " * (size - i) +
                   ch +
                   " " * (2 * i - 1) +
                   ch +
                   " " * (size - i))
        result.append(row)

    # Bottom half (mirror of top minus middle)
    for i in range(size - 1, -1, -1):
        ch = chr(ord('A') + i)
        if i == 0:
            row = " " * (size - i) + ch + " " * (size - i)
        else:
            row = (" " * (size - i) +
                   ch +
                   " " * (2 * i - 1) +
                   ch +
                   " " * (size - i))
        result.append(row)

    return result