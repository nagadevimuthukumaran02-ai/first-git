import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

class Poker {

    private final List<Hand> hands;

    Poker(List<String> handStrings) {
        this.hands = handStrings.stream()
                .map(Hand::new)
                .collect(Collectors.toList());
    }

    List<String> getBestHands() {
        if (hands.isEmpty()) {
            return Collections.emptyList();
        }

        // Find the maximum ranked hand
        Hand bestHand = Collections.max(hands);

        // Return all hands that tie with the best hand
        return hands.stream()
                .filter(h -> h.compareTo(bestHand) == 0)
                .map(Hand::getOriginalString)
                .collect(Collectors.toList());
    }

    // Helper class to parse, score, and compare poker hands
    private static class Hand implements Comparable<Hand> {
        private final String originalString;
        private final List<Integer> ranks;
        private int handCategory; // Higher number = better hand type
        private List<Integer> tieBreakers;

        Hand(String handString) {
            this.originalString = handString;
            this.ranks = new ArrayList<>();
            
            String[] cards = handString.trim().split("\\s+");
            Map<Character, Integer> suitCounts = new HashMap<>();
            Map<Integer, Integer> rankCounts = new HashMap<>();

            for (String card : cards) {
                int rank = parseRank(card);
                char suit = card.charAt(card.length() - 1);

                ranks.add(rank);
                rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
                suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
            }

            Collections.sort(ranks, Collections.reverseOrder());
            evaluateHand(suitCounts, rankCounts);
        }

        public String getOriginalString() {
            return originalString;
        }

        private int parseRank(String card) {
            String rankStr = card.substring(0, card.length() - 1);
            switch (rankStr) {
                case "J": return 11;
                case "Q": return 12;
                case "K": return 13;
                case "A": return 14;
                default: return Integer.parseInt(rankStr);
            }
        }

        private void evaluateHand(Map<Character, Integer> suitCounts, Map<Integer, Integer> rankCounts) {
            boolean isFlush = suitCounts.values().stream().anyMatch(count -> count == 5);

            // Check for Straight (including Ace-low straight A-2-3-4-5)
            List<Integer> distinctRanks = new ArrayList<>(rankCounts.keySet());
            Collections.sort(distinctRanks, Collections.reverseOrder());

            boolean isStraight = false;
            List<Integer> straightRanks = new ArrayList<>();

            if (distinctRanks.size() == 5) {
                if (distinctRanks.get(0) - distinctRanks.get(4) == 4) {
                    isStraight = true;
                    straightRanks = new ArrayList<>(distinctRanks);
                } else if (distinctRanks.equals(List.of(14, 5, 4, 3, 2))) { // Ace-low straight
                    isStraight = true;
                    straightRanks = List.of(5, 4, 3, 2, 1); // Ace acts as 1 here
                }
            }

            // Categorize frequencies (e.g., Four of a Kind, Full House, Pairs)
            List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(rankCounts.entrySet());
            frequencyList.sort((a, b) -> {
                int freqCompare = Integer.compare(b.getValue(), a.getValue());
                if (freqCompare != 0) return freqCompare;
                return Integer.compare(b.getKey(), a.getKey());
            });

            tieBreakers = new ArrayList<>();

            if (isStraight && isFlush) {
                handCategory = 8; // Straight Flush
                tieBreakers = straightRanks;
            } else if (frequencyList.get(0).getValue() == 4) {
                handCategory = 7; // Four of a Kind
                addTieBreakers(frequencyList);
            } else if (frequencyList.get(0).getValue() == 3 && frequencyList.get(1).getValue() == 2) {
                handCategory = 6; // Full House
                addTieBreakers(frequencyList);
            } else if (isFlush) {
                handCategory = 5; // Flush
                tieBreakers = ranks;
            } else if (isStraight) {
                handCategory = 4; // Straight
                tieBreakers = straightRanks;
            } else if (frequencyList.get(0).getValue() == 3) {
                handCategory = 3; // Three of a Kind
                addTieBreakers(frequencyList);
            } else if (frequencyList.get(0).getValue() == 2 && frequencyList.get(1).getValue() == 2) {
                handCategory = 2; // Two Pair
                addTieBreakers(frequencyList);
            } else if (frequencyList.get(0).getValue() == 2) {
                handCategory = 1; // One Pair
                addTieBreakers(frequencyList);
            } else {
                handCategory = 0; // High Card
                tieBreakers = ranks;
            }
        }

        private void addTieBreakers(List<Map.Entry<Integer, Integer>> frequencyList) {
            for (Map.Entry<Integer, Integer> entry : frequencyList) {
                tieBreakers.add(entry.getKey());
            }
        }

        @Override
        public int compareTo(Hand other) {
            if (this.handCategory != other.handCategory) {
                return Integer.compare(this.handCategory, other.handCategory);
            }
            // Compare tie-breaker lists lexicographically
            for (int i = 0; i < this.tieBreakers.size(); i++) {
                int comp = Integer.compare(this.tieBreakers.get(i), other.tieBreakers.get(i));
                if (comp != 0) {
                    return comp;
                }
            }
            return 0;
        }
    }
}