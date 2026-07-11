def to_rna(dna: str) -> str:
    complement = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U",
    }

    rna = []
    for nucleotide in dna:
        rna.append(complement[nucleotide])

    return "".join(rna)
