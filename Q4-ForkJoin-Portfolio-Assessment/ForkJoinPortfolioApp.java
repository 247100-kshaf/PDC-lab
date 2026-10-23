import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int size = 10_000;
        double[] assets = new double[size];
        Random random = new Random(42);

        for (int i = 0; i < size; i++) {
            assets[i] = 50 + random.nextDouble() * 150;
        }

        System.out.println("Generated " + size + " synthetic asset values.");

        ForkJoinPool pool = new ForkJoinPool();
        long parStart = System.nanoTime();
        double parallelRisk = pool.invoke(
                new ParallelRiskCalculator(assets, 0, assets.length));
        long parTime = System.nanoTime() - parStart;

        System.out.println("Parallel total risk: " + parallelRisk);

        long serStart = System.nanoTime();
        double serialRisk = 0.0;

        for (double a : assets) {
            serialRisk += a * a * 0.0001;
        }

        long serTime = System.nanoTime() - serStart;
        System.out.println("Serial total risk:   " + serialRisk);
        System.out.println("Parallel time: " + parTime / 1000
                + " us | Serial time: " + serTime / 1000 + " us");

        double riskCap = 180.0;
        pool.invoke(new PortfolioThresholdTask(assets, 0, assets.length, riskCap));
        System.out.println("Applied 5% stress penalty to assets above " + riskCap);
        pool.shutdown();
    }
}
