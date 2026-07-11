def is_paired(input_string):
    stack = []
    brackets = {
        ')': '(',
        ']': '[',
        '}': '{'
    }

    for char in input_string:
        if char in brackets.values():
            stack.append(char)
        elif char in brackets:
            if not stack or stack[-1] != brackets[char]:
                return False
            stack.pop()
    return len(stack) == 0