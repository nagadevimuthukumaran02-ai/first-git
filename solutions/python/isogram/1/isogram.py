def is_isogram(word):
    """Check if the given word or phrase is an isogram."""
    # Normalize: lowercase and remove spaces and hyphens
    cleaned = word.replace(" ", "").replace("-", "").lower()
    
    # Use a set to track seen letters
    seen = set()
    for char in cleaned:
        if char in seen:
            return False
        seen.add(char)
    return True