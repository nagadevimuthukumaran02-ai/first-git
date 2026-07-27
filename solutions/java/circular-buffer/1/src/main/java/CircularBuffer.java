class CircularBuffer<T> {

    private final Object[] buffer;
    private final int capacity;

    private int head;
    private int tail;
    private int count;

    CircularBuffer(final int size) {
        buffer = new Object[size];
        capacity = size;
        head = 0;
        tail = 0;
        count = 0;
    }

    @SuppressWarnings("unchecked")
    T read() throws BufferIOException {
        if (count == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }

        T value = (T) buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        count--;

        return value;
    }

    void write(T data) throws BufferIOException {
        if (count == capacity) {
            throw new BufferIOException("Tried to write to full buffer");
        }

        buffer[tail] = data;
        tail = (tail + 1) % capacity;
        count++;
    }

    void overwrite(T data) {
        if (count == capacity) {
            buffer[head] = data;
            head = (head + 1) % capacity;
            tail = head;
        } else {
            buffer[tail] = data;
            tail = (tail + 1) % capacity;
            count++;
        }
    }

    void clear() {
        for (int i = 0; i < capacity; i++) {
            buffer[i] = null;
        }

        head = 0;
        tail = 0;
        count = 0;
    }
}