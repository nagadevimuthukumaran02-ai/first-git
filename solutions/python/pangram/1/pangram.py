import string

def is_pangram(sentence: str) -> bool:
    alphabet = set(string.ascii_lowercase)
    letters_in_sentence = {ch.lower() for ch in sentence if ch.isalpha()}
    return alphabet.issubset(letters_in_sentence)
