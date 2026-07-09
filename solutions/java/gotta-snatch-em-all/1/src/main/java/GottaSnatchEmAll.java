import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GottaSnatchEmAll {

    public static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    public static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    public static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        boolean iHaveCardTheyNeed = false;
        boolean theyHaveCardINeed = false;

        for (String card : myCollection) {
            if (!theirCollection.contains(card)) {
                iHaveCardTheyNeed = true;
                break;
            }
        }

        for (String card : theirCollection) {
            if (!myCollection.contains(card)) {
                theyHaveCardINeed = true;
                break;
            }
        }

        return iHaveCardTheyNeed && theyHaveCardINeed;
    }

    public static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> result = new HashSet<>(collections.get(0));

        for (Set<String> collection : collections) {
            result.retainAll(collection);
        }

        return result;
    }

    public static Set<String> allCards(List<Set<String>> collections) {
        Set<String> result = new HashSet<>();

        for (Set<String> collection : collections) {
            result.addAll(collection);
        }

        return result;
    }
}