class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;

    void push(T value) {
        Element<T> newNode = new Element<>(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    T pop() {
        if (tail == null) {
            throw new IllegalStateException("The list is empty.");
        }

        T value = tail.value;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return value;
    }

    void unshift(T value) {
        Element<T> newNode = new Element<>(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    T shift() {
        if (head == null) {
            throw new IllegalStateException("The list is empty.");
        }

        T value = head.value;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return value;
    }

    void delete(T value) {
        Element<T> current = head;

        while (current != null) {
            if ((value == null && current.value == null)
                    || (value != null && value.equals(current.value))) {

                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return;
            }

            current = current.next;
        }

        throw new IllegalArgumentException("Value not found.");
    }

    int size() {
        return size;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        private Element(T value) {
            this.value = value;
        }
    }
}