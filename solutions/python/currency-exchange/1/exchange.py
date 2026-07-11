def exchange_money(budget: float, exchange_rate: float) -> float:
    """Calculate the value of exchanged currency."""
    return budget / exchange_rate


def get_change(budget: float, exchanging_value: float) -> float:
    """Calculate the amount of money left after an exchange."""
    return budget - exchanging_value


def get_value_of_bills(denomination: int, number_of_bills: int) -> int:
    """Calculate the total value of bills received."""
    return denomination * number_of_bills


def get_number_of_bills(budget: float, denomination: int) -> int:
    """Calculate how many full bills of `denomination` you can get from budget."""
    return budget // denomination


def get_leftover_of_bills(budget: float, denomination: int) -> float:
    """Calculate the leftover amount after exchanging into full bills."""
    return budget % denomination


def exchangeable_value(budget: float, exchange_rate: float, spread: int, denomination: int) -> int:
    """
    Calculate the maximum value you can get from exchanging currency.

    :param budget: amount of money to exchange
    :param exchange_rate: rate of exchange
    :param spread: percentage fee taken by the exchange
    :param denomination: value of a single bill
    :return: the maximum value you can receive in bills
    """
    rate_with_fee = exchange_rate * (1 + spread / 100)
    exchanged = budget / rate_with_fee
    return int(exchanged // denomination * denomination)