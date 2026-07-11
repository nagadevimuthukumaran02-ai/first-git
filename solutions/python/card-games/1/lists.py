def get_rounds(number):
    """Create a list containing the current and next two round numbers."""
    return [number, number + 1, number + 2]


def concatenate_rounds(rounds_1, rounds_2):
    """Concatenate two lists of round numbers."""
    return rounds_1 + rounds_2


def list_contains_round(rounds, number):
    """Check if the list of rounds contains the specified number."""
    return number in rounds


def card_average(hand):
    """Calculate and return the average card value from the list."""
    return sum(hand) / len(hand)


def approx_average_is_average(hand):
    """Return True if an approximate average equals the actual average."""
    actual_avg = card_average(hand)
    first_last_avg = (hand[0] + hand[-1]) / 2
    middle_card = hand[len(hand) // 2]
    return actual_avg == first_last_avg or actual_avg == middle_card


def average_even_is_average_odd(hand):
    """Return True if average of even-indexed equals odd-indexed card values."""
    even_cards = hand[::2]
    odd_cards = hand[1::2]
    return card_average(even_cards) == card_average(odd_cards)


def maybe_double_last(hand):
    """Double the value of the last card if it is a Jack (11)."""
    if hand[-1] == 11:
        hand[-1] *= 2
    return hand