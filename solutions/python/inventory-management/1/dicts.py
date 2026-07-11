def create_inventory(items):
    """Create an inventory from a list of items."""
    inventory = {}
    for item in items:
        inventory[item] = inventory.get(item, 0) + 1
    return inventory


def add_items(inventory, items):
    """Add items to an existing inventory."""
    for item in items:
        inventory[item] = inventory.get(item, 0) + 1
    return inventory


def decrement_items(inventory, items):
    """Decrement item quantities (cannot go below zero)."""
    for item in items:
        if item in inventory:
            inventory[item] = max(0, inventory[item] - 1)
    return inventory


def remove_item(inventory, item):
    """Completely remove an item from the inventory if it exists."""
    if item in inventory:
        del inventory[item]
    return inventory

 
def list_inventory(inventory):
    """Return inventory as list of (item_name, quantity) tuples."""
    return list(inventory.items())
def create_inventory(items):
    """Create an inventory from a list of items."""
    inventory = {}
    for item in items:
        inventory[item] = inventory.get(item, 0) + 1
    return inventory


def add_items(inventory, items):
    """Add items to an existing inventory."""
    for item in items:
        inventory[item] = inventory.get(item, 0) + 1
    return inventory


def decrement_items(inventory, items):
    """Decrement item quantities (cannot go below zero)."""
    for item in items:
        if item in inventory:
            inventory[item] = max(0, inventory[item] - 1)
    return inventory


def remove_item(inventory, item):
    """Completely remove an item from the inventory if it exists."""
    if item in inventory:
        del inventory[item]
    return inventory


def list_inventory(inventory):
    """Return inventory as list of (item_name, quantity) tuples, excluding zero quantities."""
    return [(item, count) for item, count in inventory.items() if count > 0]
