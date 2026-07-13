import java.util.*;

class RelativeDistance {

    private final Map<String, Set<String>> graph = new HashMap<>();

    RelativeDistance(Map<String, List<String>> familyTree) {

        for (Map.Entry<String, List<String>> entry : familyTree.entrySet()) {

            String parent = entry.getKey();
            List<String> children = entry.getValue();

            graph.putIfAbsent(parent, new HashSet<>());

            // Parent <-> Child connections
            for (String child : children) {
                graph.putIfAbsent(child, new HashSet<>());

                graph.get(parent).add(child);
                graph.get(child).add(parent);
            }

            // Sibling <-> Sibling connections
            for (int i = 0; i < children.size(); i++) {
                for (int j = i + 1; j < children.size(); j++) {
                    String a = children.get(i);
                    String b = children.get(j);

                    graph.get(a).add(b);
                    graph.get(b).add(a);
                }
            }
        }
    }

    int degreeOfSeparation(String personA, String personB) {

        if (personA.equals(personB)) {
            return 0;
        }

        if (!graph.containsKey(personA) || !graph.containsKey(personB)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Queue<Integer> distance = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(personA);
        distance.offer(0);
        visited.add(personA);

        while (!queue.isEmpty()) {

            String current = queue.poll();
            int dist = distance.poll();

            for (String next : graph.get(current)) {

                if (visited.contains(next)) {
                    continue;
                }

                if (next.equals(personB)) {
                    return dist + 1;
                }

                visited.add(next);
                queue.offer(next);
                distance.offer(dist + 1);
            }
        }

        return -1;
    }
}