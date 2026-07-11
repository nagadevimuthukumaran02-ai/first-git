import re

def abbreviate(phrase):
    # Replace hyphens with spaces (word separators)
    phrase = phrase.replace("-", " ")

    # Remove all punctuation except letters, numbers, and spaces
    phrase = re.sub(r"[^A-Za-z0-9 ]", "", phrase)

    # Split into words
    words = phrase.split()

    # Take the first letter of each word and uppercase it
    acronym = "".join(word[0].upper() for word in words)

    return acronym
