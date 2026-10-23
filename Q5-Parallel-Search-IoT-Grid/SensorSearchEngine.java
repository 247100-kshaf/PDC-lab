public abstract class SensorSearchEngine implements Runnable {
    protected double[] sensorData;
    protected int startIdx;
    protected int endIdx;
    protected double result;

    public SensorSearchEngine(double[] sensorData, int startIdx, int endIdx) {
        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }
