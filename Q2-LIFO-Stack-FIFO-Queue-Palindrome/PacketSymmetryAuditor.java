public class PacketSymmetryAuditor {

    public static boolean isSymmetric(String payload) {
        int n = payload.length();
        TransactionStack stack = new TransactionStack(n);
        RequestQueue queue = new RequestQueue(n);

        for (int i = 0; i < n; i++) {
            char c = payload.charAt(i);
            stack.push(c);
            queue.enqueue(c);
        }
