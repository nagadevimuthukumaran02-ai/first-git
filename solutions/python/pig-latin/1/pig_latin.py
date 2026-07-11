def translate(text):
    vowels = ("a", "e", "i", "o", "u")

    def convert_word(word):

        # Rule 1 → starts with vowel or "xr"/"yt"
        if word.startswith(vowels) or word.startswith(("xr", "yt")):
            return word + "ay"

        # Rule 3 → consonants followed by "qu"
        if word.startswith("qu"):
            return word[2:] + "quay"

        # Otherwise → find where vowel or 'y' (only after index>0) begins
        index = 0
        while index < len(word):
            # Rule 4 → 'y' works as vowel only if not at start
            if (word[index] in vowels) or (word[index] == 'y' and index > 0):
                break

            # Special case → "qu" inside consonant cluster
            if word[index:index+2] == "qu":
                index += 2
                break

            index += 1

        # Rule 2 → move leading consonants to end
        return word[index:] + word[:index] + "ay"

    # Apply translation word-by-word
    return " ".join(convert_word(word) for word in text.split())