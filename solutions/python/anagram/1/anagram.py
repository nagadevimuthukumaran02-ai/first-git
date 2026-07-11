def find_anagrams(target, candidates):
    target_lower = target.lower()
    target_sorted = sorted(target_lower)

    result = []

    for word in candidates:
        word_lower = word.lower()

        # Skip if the word is exactly the same as the target
        if word_lower == target_lower:
            continue
        if sorted(word_lower) == target_sorted:
            result.append(word)

    return result
