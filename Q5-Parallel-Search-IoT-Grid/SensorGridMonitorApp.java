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
