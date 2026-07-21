import java.util.List;

class RelationshipComputer<T> {

    Relationship computeRelationship(List<T> firstList, List<T> secondList) {

        if (firstList.equals(secondList)) {
            return Relationship.EQUAL;
        }

        if (isSublist(firstList, secondList)) {
            return Relationship.SUBLIST;
        }

        if (isSublist(secondList, firstList)) {
            return Relationship.SUPERLIST;
        }

        return Relationship.UNEQUAL;
    }

    private boolean isSublist(List<T> subList, List<T> mainList) {

        if (subList.isEmpty()) {
            return true;
        }

        if (subList.size() > mainList.size()) {
            return false;
        }

        for (int i = 0; i <= mainList.size() - subList.size(); i++) {
            boolean match = true;

            for (int j = 0; j < subList.size(); j++) {
                if (!mainList.get(i + j).equals(subList.get(j))) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return true;
            }
        }

        return false;
    }
}