def add_prefix_un(word):
    return "un" + word

def make_word_groups(vocab_words):
    prefix = vocab_words[0]
    return ' :: '.join([prefix] + [prefix + word for word in vocab_words[1:]])

def remove_suffix_ness(word):
    word = word[:-4]  # remove 'ness'
    if word.endswith('i'):
        word = word[:-1] + 'y'
    return word

def adjective_to_verb(sentence, index):
    word = sentence.replace('.', '').split()[index]
    return word + 'en'