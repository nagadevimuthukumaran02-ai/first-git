def get_coordinate(treasure_coordinate):
    """Return only the coordinate part from the (treasure, coordinate) tuple."""
    return treasure_coordinate[1]


def convert_coordinate(coordinate):
    """Convert a coordinate string like '2A' into a tuple ('2', 'A')."""
    return tuple(coordinate)


def compare_records(azara_record, rui_record):
    """Compare Azara’s record and Rui’s record.

    Returns True if their coordinates match, otherwise False.
    """
    azara_coord = convert_coordinate(azara_record[1])
    return azara_coord == rui_record[1]


def create_record(azara_record, rui_record):
    """Combine records if coordinates match; otherwise return 'not a match'."""
    if compare_records(azara_record, rui_record):
        return azara_record + rui_record
    else:
        return "not a match"


def clean_up(combined_record_group):
    """Clean up all combined records for printing.

    Returns a multi-line string with (treasure, location, coordinate, quadrant) tuples.
    """
    cleaned_records = []
    for record in combined_record_group:
        cleaned_records.append(str((record[0], record[2], record[3], record[4])))
    return "\n".join(cleaned_records) + "\n"
