CODON_TABLE = {
    "AUG": "Methionine",
    "UUU": "Phenylalanine", "UUC": "Phenylalanine",
    "UUA": "Leucine", "UUG": "Leucine",
    "UCU": "Serine", "UCC": "Serine", "UCA": "Serine", "UCG": "Serine",
    "UAU": "Tyrosine", "UAC": "Tyrosine",
    "UGU": "Cysteine", "UGC": "Cysteine",
    "UGG": "Tryptophan",
    "UAA": "STOP", "UAG": "STOP", "UGA": "STOP",
}

def proteins(strand):
    result = []
    
    # Process in steps of 3 nucleotides
    for i in range(0, len(strand), 3):
        codon = strand[i:i+3]

        # Ignore incomplete codons (e.g., leftover 1–2 nucleotides)
        if len(codon) < 3:
            break

        amino_acid = CODON_TABLE.get(codon)

        # Stop translation if STOP codon appears
        if amino_acid == "STOP":
            break

        result.append(amino_acid)

    return result
