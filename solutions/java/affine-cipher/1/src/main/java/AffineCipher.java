class AffineCipher {

    private static final int M = 26;

    static String encode(String phrase, int a, int b) {
        checkCoprime(a);

        StringBuilder result = new StringBuilder();
        int count = 0;

        for (char c : phrase.toLowerCase().toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
                count++;
            } else if (Character.isLetter(c)) {
                int i = c - 'a';
                int encrypted = (a * i + b) % M;
                result.append((char) ('a' + encrypted));
                count++;
            } else {
                continue;
            }

            if (count % 5 == 0) {
                result.append(' ');
            }
        }

        return result.toString().trim();
    }

    static String decode(String phrase, int a, int b) {
        checkCoprime(a);

        int aInverse = modInverse(a, M);
        StringBuilder result = new StringBuilder();

        for (char c : phrase.toLowerCase().toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else if (Character.isLetter(c)) {
                int y = c - 'a';
                int decrypted = Math.floorMod(aInverse * (y - b), M);
                result.append((char) ('a' + decrypted));
            }
        }

        return result.toString();
    }

    private static void checkCoprime(int a) {
        if (gcd(a, M) != 1) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int modInverse(int a, int m) {
        a = Math.floorMod(a, m);
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
    }
}