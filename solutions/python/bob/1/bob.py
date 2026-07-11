def response(hey_bob):
    # Remove whitespace from the ends
    phrase = hey_bob.strip()

    # 1. Silence
    if phrase == "":
        return "Fine. Be that way!"

    # Check if it's yelling (all letters are uppercase)
    is_yelling = phrase.isupper()
    # Check if it's a question (ends with '?')
    is_question = phrase.endswith("?")

    # 2. Yelled question
    if is_yelling and is_question:
        return "Calm down, I know what I'm doing!"

    # 3. Yelling
    if is_yelling:
        return "Whoa, chill out!"

    # 4. Question
    if is_question:
        return "Sure."

    # 5. Anything else
    return "Whatever."
