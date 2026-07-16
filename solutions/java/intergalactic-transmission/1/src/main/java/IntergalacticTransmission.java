import java.util.ArrayList;
import java.util.List;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {
        if (message == null || message.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> bitStream = new ArrayList<>();
        // Convert the input message bytes into a continuous stream of bits (MSB to LSB)
        for (int b : message) {
            for (int i = 7; i >= 0; i--) {
                bitStream.add((b >> i) & 1);
            }
        }

        List<Integer> transmission = new ArrayList<>();
        // Process the bit stream in chunks of 7 bits
        for (int i = 0; i < bitStream.size(); i += 7) {
            int chunkValue = 0;
            int onesCount = 0;

            for (int j = 0; j < 7; j++) {
                int bit = 0;
                if (i + j < bitStream.size()) {
                    bit = bitStream.get(i + j);
                } // Remaining bits are implicitly padded with 0 as default
                
                chunkValue = (chunkValue << 1) | bit;
                if (bit == 1) {
                    onesCount++;
                }
            }

            // Determine parity bit to ensure an even number of 1s total
            int parityBit = (onesCount % 2 == 0) ? 0 : 1;

            // Combine the 7-bit chunk with the parity bit at the rightmost position
            int transmissionBlock = (chunkValue << 1) | parityBit;
            transmission.add(transmissionBlock);
        }

        return transmission;
    }

    public static List<Integer> decodeSequence(List<Integer> sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> dataBits = new ArrayList<>();
        for (int block : sequence) {
            // Validate parity: Count total number of 1s in the 8-bit block
            int onesCount = Integer.bitCount(block & 0xFF);
            if (onesCount % 2 != 0) {
                throw new IllegalArgumentException("Corrupted message: Odd parity detected.");
            }

            // Extract the 7 data bits by shifting out the parity bit
            int dataChunk = (block >> 1) & 0x7F;
            for (int i = 6; i >= 0; i--) {
                dataBits.add((dataChunk >> i) & 1);
            }
        }

        List<Integer> decodedMessage = new ArrayList<>();
        // Reconstruct the original 8-bit bytes from the data bits
        for (int i = 0; i + 7 < dataBits.size(); i += 8) {
            int byteValue = 0;
            for (int j = 0; j < 8; j++) {
                byteValue = (byteValue << 1) | dataBits.get(i + j);
            }
            decodedMessage.add(byteValue);
        }

        return decodedMessage;
    }
}