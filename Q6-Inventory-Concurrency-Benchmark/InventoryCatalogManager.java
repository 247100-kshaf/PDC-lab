import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {
    private static final int THREADS = 10;
    private static final int OPS_PER_THREAD = 50_000;
    private static final int PRODUCTS = 1000;

    private static long benchmark(Map<Integer, Integer> map) throws Exception {
        for (int i = 0; i < PRODUCTS; i++) {
            map.put(i, 100);
        }

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        long start = System.nanoTime();

        for (int t = 0; t < THREADS; t++) {
            executor.submit(() -> {
                ThreadLocalRandom rnd = ThreadLocalRandom.current();

                for (int i = 0; i < OPS_PER_THREAD; i++) {
                    int id = rnd.nextInt(PRODUCTS);
                    map.get(id);
                    map.merge(id, 1, Integer::sum);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
        return (System.nanoTime() - start) / 1_000_000;
    }

    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> syncMap =
                Collections.synchronizedMap(new HashMap<>());

        long syncTime = benchmark(syncMap);
        System.out.println("Collections.synchronizedMap : " + syncTime + " ms");
