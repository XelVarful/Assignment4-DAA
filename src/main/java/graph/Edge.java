package graph;

public class Edge {
    public int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return from + " -> " + to + " (" + weight + ")";
    }
}
