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
