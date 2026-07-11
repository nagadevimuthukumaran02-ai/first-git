
def capitalize_title(title):
    """Convert the first letter of each word in the title to uppercase if needed."""
    return title.title()

def check_sentence_ending(sentence):
    """Check the ending of the sentence to verify that a period is present."""
    return sentence.endswith('.')

def clean_up_spacing(sentence):
    """Remove leading and trailing whitespace from the sentence."""
    return sentence.strip()

def replace_word_choice(sentence, old_word, new_word):
    """Replace a word in the sentence with a new one."""
    return sentence.replace(old_word, new_word)
