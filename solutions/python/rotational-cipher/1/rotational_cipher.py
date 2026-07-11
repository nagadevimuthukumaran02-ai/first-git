def rotate(text: str, key: int) -> str:
    result = []

    for ch in text:
        if ch.isalpha():  
            base = ord('A') if ch.isupper() else ord('a')
            # Shift within alphabet
            new_char = chr((ord(ch) - base + key) % 26 + base)
            result.append(new_char)
        else:
            # Leave digits, spaces, punctuation unchanged
            result.append(ch)

    return "".join(result)
