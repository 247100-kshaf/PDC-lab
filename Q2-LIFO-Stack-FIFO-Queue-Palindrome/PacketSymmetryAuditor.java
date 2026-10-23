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

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] packets = { "racecar", "level", "packet", "A1B2B1A", "NODE" };
        for (String p : packets) {
            String result = isSymmetric(p) ? "SYMMETRIC" : "NOT symmetric";
            System.out.println("Payload \"" + p + "\" -> " + result);
        }
    }
}
