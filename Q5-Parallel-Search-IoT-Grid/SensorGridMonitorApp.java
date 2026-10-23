import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        int size = 50_000;
        int workers = 4;
        double[] sensorData = new double[size];
        Random random = new Random(7);

        for (int i = 0; i < size; i++) {
            sensorData[i] = -10 + random.nextDouble() * 110;
        }

        System.out.println("Generated " + size + " sensor readings.");

        int chunk = size / workers;

        ParallelMinSearch[] minTasks = new ParallelMinSearch[workers];
        Thread[] minThreads = new Thread[workers];

        for (int w = 0; w < workers; w++) {
            int s = w * chunk;
            int e = (w == workers - 1) ? size : s + chunk;

            minTasks[w] = new ParallelMinSearch(sensorData, s, e);
            minThreads[w] = new Thread(minTasks[w], "Min-Worker-" + w);
            minThreads[w].start();
        }

        double globalMin = Double.MAX_VALUE;

        for (int w = 0; w < workers; w++) {
            minThreads[w].join();
            globalMin = Math.min(globalMin, minTasks[w].getResult());
        }

        System.out.println("Minimum temperature across grid: " + globalMin);
