import java.util.*;

class TwoBucket {

    private final Result result;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        this.result = solve(bucketOneCap, bucketTwoCap, desiredLiters, startBucket);
    }

    Result getResult() {
        return result;
    }

    private Result solve(int cap1, int cap2, int goal, String startBucket) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State start;
        if ("one".equals(startBucket)) {
            start = new State(cap1, 0, 1);
        } else {
            start = new State(0, cap2, 1);
        }

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.one == goal) {
                return new Result(current.moves, "one", current.two);
            }
            if (current.two == goal) {
                return new Result(current.moves, "two", current.one);
            }

            for (State next : nextStates(current, cap1, cap2, startBucket)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        throw new UnreachableGoalException();
    }

    private List<State> nextStates(State s, int cap1, int cap2, String startBucket) {
        List<State> states = new ArrayList<>();

        // Fill bucket one
        addState(states, new State(cap1, s.two, s.moves + 1), cap1, cap2, startBucket);

        // Fill bucket two
        addState(states, new State(s.one, cap2, s.moves + 1), cap1, cap2, startBucket);

        // Empty bucket one
        addState(states, new State(0, s.two, s.moves + 1), cap1, cap2, startBucket);

        // Empty bucket two
        addState(states, new State(s.one, 0, s.moves + 1), cap1, cap2, startBucket);

        // Pour one -> two
        int pour = Math.min(s.one, cap2 - s.two);
        addState(states,
                new State(s.one - pour, s.two + pour, s.moves + 1),
                cap1, cap2, startBucket);

        // Pour two -> one
        pour = Math.min(s.two, cap1 - s.one);
        addState(states,
                new State(s.one + pour, s.two - pour, s.moves + 1),
                cap1, cap2, startBucket);

        return states;
    }

    private void addState(List<State> states, State state,
                          int cap1, int cap2, String startBucket) {

        if ("one".equals(startBucket)) {
            if (state.one == 0 && state.two == cap2) {
                return;
            }
        } else {
            if (state.two == 0 && state.one == cap1) {
                return;
            }
        }

        states.add(state);
    }

    private static class State {
        final int one;
        final int two;
        final int moves;

        State(int one, int two, int moves) {
            this.one = one;
            this.two = two;
            this.moves = moves;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof State)) {
                return false;
            }
            State other = (State) obj;
            return one == other.one && two == other.two;
        }

        @Override
        public int hashCode() {
            return Objects.hash(one, two);
        }
    }
}