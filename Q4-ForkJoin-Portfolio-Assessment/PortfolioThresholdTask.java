import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private final double[] assets;
    private final int start;
    private final int end;
    private final double riskCap;

    public PortfolioThresholdTask(double[] assets, int start, int end, double riskCap) {
        this.assets = assets;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {
        int length = end - start;

        if (length <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (assets[i] > riskCap) {
                    assets[i] = assets[i] * 1.05;
                }
            }
            return;
        }

        int mid = start + length / 2;
        invokeAll(new PortfolioThresholdTask(assets, start, mid, riskCap),
                  new PortfolioThresholdTask(assets, mid, end, riskCap));
    }
}
