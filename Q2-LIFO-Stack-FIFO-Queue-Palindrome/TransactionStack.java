public class TransactionStack {
    private char[] data;
    private int top;
    private int capacity;

    public TransactionStack(int capacity) {
        this.capacity = capacity;
        this.data = new char[capacity];
        this.top = -1;
    }
