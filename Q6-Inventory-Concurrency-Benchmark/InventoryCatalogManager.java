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
