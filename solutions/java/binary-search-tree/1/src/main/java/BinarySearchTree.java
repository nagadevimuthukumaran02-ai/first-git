import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }

        // Exercism rule: data <= current goes left, data > current goes right
        if (value.compareTo(current.getData()) <= 0) {
            current.left = insertRec(current.getLeft(), value);
        } else {
            current.right = insertRec(current.getRight(), value);
        }

        return current;
    }

    List<T> getAsSortedList() {
        List<T> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);
        return sortedList;
    }

    private void inOrderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inOrderTraversal(node.getRight(), list);
        }
    }

    List<T> getAsLevelOrderList() {
        List<T> levelOrderList = new ArrayList<>();
        if (root == null) {
            return levelOrderList;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            levelOrderList.add(current.getData());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        return levelOrderList;
    }

    Node<T> getRoot() {
        return this.root;
    }

    static class Node<T> {
        private final T data;
        private Node<T> left;
        private Node<T> right;

        Node(T data) {
            this.data = data;
        }

        T getData() {
            return this.data;
        }

        Node<T> getLeft() {
            return this.left;
        }

        Node<T> getRight() {
            return this.right;
        }
    }
}