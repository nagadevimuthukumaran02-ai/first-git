SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4


def sublist(list_one, list_two):

    # Both lists equal
    if list_one == list_two:
        return EQUAL
    
    # Check if one list is inside another
    def is_sublist(a, b):
        # a - smaller list, b - larger list
        if len(a) == 0:
            return True
        for i in range(len(b) - len(a) + 1):
            if b[i:i + len(a)] == a:
                return True
        return False
    
    # list_one is smaller → possible SUBLIST
    if len(list_one) < len(list_two) and is_sublist(list_one, list_two):
        return SUBLIST
    
    # list_one is larger → possible SUPERLIST
    if len(list_one) > len(list_two) and is_sublist(list_two, list_one):
        return SUPERLIST
    
    # No match → UNEQUAL
    return UNEQUAL