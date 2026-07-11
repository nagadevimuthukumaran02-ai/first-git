def encode(string):
    if not string:
        return ""

    result = []
    count = 1

    for i in range(1, len(string)):
        if string[i] == string[i - 1]:
            count += 1
        else:
            # Add count only if > 1
            if count > 1:
                result.append(str(count))
            result.append(string[i - 1])
            count = 1
    if count > 1:
        result.append(str(count))
    result.append(string[-1])

    return "".join(result)


def decode(string):
    if not string:
        return ""

    result = []
    count = ""

    for char in string:
        if char.isdigit():
            count += char  # build multi-digit count
        else:
            # If count is empty, treat as 1
            num = int(count) if count else 1
            result.append(char * num)
            count = ""

    return "".join(result)
