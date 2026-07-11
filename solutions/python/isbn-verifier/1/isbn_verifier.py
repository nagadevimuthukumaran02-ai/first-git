def is_valid(isbn: str) -> bool:
    # Remove hyphens
    isbn = isbn.replace("-", "")
    
    # ISBN-10 must be exactly 10 chars
    if len(isbn) != 10:
        return False

    # First 9 chars must be digits
    if not isbn[:9].isdigit():
        return False

    # Last char can be a digit or 'X'
    if not (isbn[-1].isdigit() or isbn[-1] == "X"):
        return False

    total = 0
    for i, ch in enumerate(isbn):
        if ch == "X":
            value = 10
        else:
            value = int(ch)

        weight = 10 - i
        total += value * weight

    return total % 11 == 0
