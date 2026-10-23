public class CacheLocalityBenchmark {
    private static final int SIZE = 2000;
    private static final int ITERATIONS = 10;

    private static double[][] createMatrix() {
        double[][] m = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                m[i][j] = (i * 31 + j) % 100;
            }
        }

        return m;
    }

    private static double rowMajorSum(double[][] m) {
        double sum = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sum += m[i][j];
            }
        }

        return sum;
    }

    private static double columnMajorSum(double[][] m) {
        double sum = 0;

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                sum += m[i][j];
            }
        }

        return sum;
    }
