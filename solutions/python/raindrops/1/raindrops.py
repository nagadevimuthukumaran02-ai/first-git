def convert(number):
    """Convert a number into raindrop sounds."""
    result = ""
    
    if number % 3 == 0:
        result += "Pling"
    if number % 5 == 0:
        result += "Plang"
    if number % 7 == 0:
        result += "Plong"
    
    return result or str(number)