public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent order = new FIXOrderEvent("BROKER-01", System.currentTimeMillis(), 1500.00, "ORD-1001");
        TradeEvent quote = new ReutersQuoteEvent("REUTERS", System.currentTimeMillis(), 1500.00, 1498.50, 1501.25);
        TradeEvent[] events = { order, quote };

        System.out.println("=== Dispatching Trade Events ===");
        for (TradeEvent event : events) {
            event.dispatch();
        }
    }
}
