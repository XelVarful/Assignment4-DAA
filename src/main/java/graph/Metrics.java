package graph;

public class Metrics {
    private static long startTime;
    private static long endTime;

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    public static void stopTimer() {
        endTime = System.nanoTime();
    }

    public static long getElapsedMillis() {
        return (endTime - startTime) / 1_000_000;
    }
}
