import java.security.SecureRandom;

public class Cipher {

    private static final int KEY_LENGTH = 100;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final String key;

    public Cipher() {
        StringBuilder sb = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            sb.append((char) ('a' + RANDOM.nextInt(26)));
        }
        this.key = sb.toString();
    }

    public Cipher(String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+")) {
            throw new IllegalArgumentException("Key must contain only lowercase letters.");
        }
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int shift = key.charAt(i % key.length()) - 'a';
            char c = plainText.charAt(i);
            char encoded = (char) ((c - 'a' + shift) % 26 + 'a');
            result.append(encoded);
        }

        return result.toString();
    }

    public String decode(String cipherText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            int shift = key.charAt(i % key.length()) - 'a';
            char c = cipherText.charAt(i);
            char decoded = (char) ((c - 'a' - shift + 26) % 26 + 'a');
            result.append(decoded);
        }

        return result.toString();
    }
}