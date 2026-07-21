import java.util.ArrayList;
import java.util.List;

class Dominoes {

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes == null) {
            throw new ChainNotFoundException("No domino chain found.");
        }
        
        if (inputDominoes.isEmpty()) {
            return new ArrayList<>();
        }

        List<Domino> chain = new ArrayList<>();
        boolean[] used = new boolean[inputDominoes.size()];

        if (backtrack(inputDominoes, used, chain)) {
            return chain;
        }

        // Fix the message here to match the test requirement
        throw new ChainNotFoundException("No domino chain found.");
    }

    private boolean backtrack(List<Domino> dominoes, boolean[] used, List<Domino> chain) {
        if (chain.size() == dominoes.size()) {
            return chain.get(0).getLeft() == chain.get(chain.size() - 1).getRight();
        }

        for (int i = 0; i < dominoes.size(); i++) {
            if (!used[i]) {
                Domino d = dominoes.get(i);

                if (chain.isEmpty()) {
                    used[i] = true;
                    chain.add(d);
                    if (backtrack(dominoes, used, chain)) return true;
                    chain.remove(chain.size() - 1);
                    chain.add(new Domino(d.getRight(), d.getLeft()));
                    if (backtrack(dominoes, used, chain)) return true;
                    chain.remove(chain.size() - 1);
                    used[i] = false;
                } else {
                    int requiredLeft = chain.get(chain.size() - 1).getRight();

                    if (d.getLeft() == requiredLeft) {
                        used[i] = true;
                        chain.add(d);
                        if (backtrack(dominoes, used, chain)) return true;
                        chain.remove(chain.size() - 1);
                        used[i] = false;
                    } else if (d.getRight() == requiredLeft) {
                        used[i] = true;
                        chain.add(new Domino(d.getRight(), d.getLeft()));
                        if (backtrack(dominoes, used, chain)) return true;
                        chain.remove(chain.size() - 1);
                        used[i] = false;
                    }
                }
            }
        }
        return false;
    }
}