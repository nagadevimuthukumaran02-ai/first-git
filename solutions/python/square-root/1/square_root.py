def square_root(number):
    # Use integer binary search to find the exact square root
    low, high = 1, number

    while low <= high:
        mid = (low + high) // 2
        sq = mid * mid

        if sq == number:
            return mid
        elif sq < number:
            low = mid + 1
        else:
            high = mid - 1
