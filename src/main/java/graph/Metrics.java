package graph;

public class Metrics {
    public long dfsCalls = 0;
    public long relaxations = 0;
    private long start, end;

    public void startTimer() {
        start = System.nanoTime();
    }

    public void stopTimer() {
        end = System.nanoTime();
    }

    public long elapsedTime() {
        return end - start;
    }

    public void print() {
        System.out.println("DFS calls: " + dfsCalls);
        System.out.println("Relaxations: " + relaxations);
        System.out.println("Time: " + (elapsedTime() / 1_000_000.0) + " ms");
    }
}
