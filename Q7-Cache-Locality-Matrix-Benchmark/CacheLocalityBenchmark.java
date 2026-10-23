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

    public static void main(String[] args) {
        double[][] matrix = createMatrix();
        long rowTotalNs = 0;
        long colTotalNs = 0;
        double checkRow = 0;
        double checkCol = 0;

        for (int it = 1; it <= ITERATIONS; it++) {
            long t1 = System.nanoTime();
            checkRow = rowMajorSum(matrix);
            long rowNs = System.nanoTime() - t1;

            long t2 = System.nanoTime();
            checkCol = columnMajorSum(matrix);
            long colNs = System.nanoTime() - t2;

            rowTotalNs += rowNs;
            colTotalNs += colNs;

            System.out.println("Iteration " + it
                    + " | Row-major: " + rowNs / 1_000_000 + " ms"
                    + " | Column-major: " + colNs / 1_000_000 + " ms");
        }

        double rowAvg = rowTotalNs / (double) ITERATIONS / 1_000_000.0;
        double colAvg = colTotalNs / (double) ITERATIONS / 1_000_000.0;

        System.out.println("Checksums match: " + (checkRow == checkCol));
        System.out.println(String.format("Average row-major:    %.2f ms", rowAvg));
        System.out.println(String.format("Average column-major: %.2f ms", colAvg));
        System.out.println(String.format("Column-major is %.2fx slower than row-major",
                colAvg / Math.max(rowAvg, 0.001)));
    }
}
