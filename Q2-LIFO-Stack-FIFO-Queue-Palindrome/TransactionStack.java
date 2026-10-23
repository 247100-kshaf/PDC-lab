public class TransactionStack {
    private char[] data;
    private int top;
    private int capacity;

    public TransactionStack(int capacity) {
        this.capacity = capacity;
        this.data = new char[capacity];
        this.top = -1;
    }

    public void push(char c) {
        if (top == capacity - 1) {
            throw new RuntimeException("Stack overflow");
        }
        data[++top] = c;
    }

    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return data[top--];
    }

    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
