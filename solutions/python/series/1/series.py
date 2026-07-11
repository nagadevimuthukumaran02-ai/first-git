def slices(series, length):
    # Error: empty series
    if series == "":
        raise ValueError("series cannot be empty")

    # Error: slice length zero
    if length == 0:
        raise ValueError("slice length cannot be zero")

    # Error: negative slice length
    if length < 0:
        raise ValueError("slice length cannot be negative")

    # Error: slice length longer than series
    if length > len(series):
        raise ValueError("slice length cannot be greater than series length")

    # Generate all contiguous slices
    return [series[i:i + length] for i in range(len(series) - length + 1)]
