import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Camicia {

    static CamiciaResult simulateGame(List<String> playerA, List<String> playerB) {
        LinkedList<String> deckA = new LinkedList<>(playerA);
        LinkedList<String> deckB = new LinkedList<>(playerB);
        List<String> pile = new ArrayList<>();

        Set<String> seenStates = new HashSet<>();
        int totalCardsPlayed = 0;
        int tricksCount = 0;

        int activePlayer = 0; // 0 for Player A, 1 for Player B
        int penaltyDue = 0;
        int penaltyIssuer = -1; // 0 for A, 1 for B

        while (true) {
            // A round starts when the pile is empty and no penalty is currently due
            if (pile.isEmpty() && penaltyDue == 0) {
                String state = activePlayer + "|" + normalize(deckA) + "|" + normalize(deckB);
                if (seenStates.contains(state)) {
                    return new CamiciaResult("loop", totalCardsPlayed, tricksCount);
                }
                seenStates.add(state);
            }

            // Check if the current active player can play a card
            LinkedList<String> activeDeck = (activePlayer == 0) ? deckA : deckB;
            LinkedList<String> opponentDeck = (activePlayer == 0) ? deckB : deckA;

            if (activeDeck.isEmpty()) {
                // Active player runs out of cards; opponent collects the central pile
                opponentDeck.addAll(pile);
                pile.clear();
                tricksCount++;

                // Game ends if the active player remains completely out of cards
                if (activeDeck.isEmpty()) {
                    return new CamiciaResult("finished", totalCardsPlayed, tricksCount);
                }

                // Otherwise, the player who collected starts the next round
                activePlayer = 1 - activePlayer;
                penaltyDue = 0;
                penaltyIssuer = -1;
                continue;
            }

            // Draw the top card
            String card = activeDeck.removeFirst();
            pile.add(card);
            totalCardsPlayed++;

            if (isPaymentCard(card)) {
                // A new penalty is issued, interrupting any active ones
                penaltyDue = getPenaltyValue(card);
                penaltyIssuer = activePlayer;
                activePlayer = 1 - activePlayer; // Turn passes to the opponent to pay
            } else {
                // Number card played
                if (penaltyDue > 0) {
                    penaltyDue--;
                    if (penaltyDue == 0) {
                        // Penalty successfully paid without interruption! Issuer collects the pile
                        LinkedList<String> issuerDeck = (penaltyIssuer == 0) ? deckA : deckB;
                        LinkedList<String> loserDeck = (penaltyIssuer == 0) ? deckB : deckA;
                        
                        issuerDeck.addAll(pile);
                        pile.clear();
                        tricksCount++;

                        if (loserDeck.isEmpty()) {
                            return new CamiciaResult("finished", totalCardsPlayed, tricksCount);
                        }

                        activePlayer = penaltyIssuer; // The issuer starts the next round
                        penaltyIssuer = -1;
                    }
                } else {
                    // Normal gameplay outside of penalties: turn simply rotates
                    activePlayer = 1 - activePlayer;
                }
            }
        }
    }

    private static boolean isPaymentCard(String card) {
        return card.equals("J") || card.equals("Q") || card.equals("K") || card.equals("A");
    }

    private static int getPenaltyValue(String card) {
        switch (card) {
            case "J": return 1;
            case "Q": return 2;
            case "K": return 3;
            case "A": return 4;
            default: return 0;
        }
    }

    private static String normalize(List<String> deck) {
        StringBuilder sb = new StringBuilder();
        for (String card : deck) {
            if (isPaymentCard(card)) {
                sb.append(card);
            } else {
                sb.append("N"); // All number cards (2-10) are treated identically
            }
        }
        return sb.toString();
    }
}