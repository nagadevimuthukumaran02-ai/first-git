def commands(binary_string):
    actions = []

    # bit → action map (right to left)
    bit_actions = [
        ("wink", 1),
        ("double blink", 2),
        ("close your eyes", 4),
        ("jump", 8)
    ]

    number = int(binary_string, 2)

    for action, value in bit_actions:
        if number & value:
            actions.append(action)

    if number & 16:
        actions.reverse()

    return actions
