import string

# Create Atbash mappings
plain = string.ascii_lowercase
cipher = plain[::-1]

encode_map = str.maketrans(plain, cipher)
decode_map = str.maketrans(cipher, plain)

def encode(text):
    # Lowercase & keep only letters and numbers
    cleaned = "".join(ch for ch in text.lower() if ch.isalnum())
    # Apply substitution
    encoded = cleaned.translate(encode_map)
    # Group in chunks of 5
    return " ".join(encoded[i:i+5] for i in range(0, len(encoded), 5))

def decode(text):
    # Remove spaces and decode
    cleaned = text.replace(" ", "")
    return cleaned.translate(decode_map)
