public class RequestQueue {
    private char[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public RequestQueue(int capacity) {
        this.capacity = capacity;
        this.data = new char[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(char c) {
        if (size == capacity) {
            throw new RuntimeException("Queue overflow");
        }
        rear = (rear + 1) % capacity;
        data[rear] = c;
        size++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        char c = data[front];
        front = (front + 1) % capacity;
        size--;
        return c;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
