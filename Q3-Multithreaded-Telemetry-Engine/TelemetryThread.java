public class TelemetryThread extends Thread {

    @Override
    public void run() {

        Runtime rt = Runtime.getRuntime();
        for (int i = 1; i <= 5; i++) {
            long usedMb = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
            int cpuLoad = (int) (Math.random() * 60) + 20;
            System.out.println("[Telemetry] " + getName()
                    + " | CPU load: " + cpuLoad + "%"
                    + " | Memory used: " + usedMb + " MB"
                    + " | Reading " + i);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
