package graph;

import java.util.*;

public class Graph {
    private final int n;
    private final List<List<Edge>> adj;

    public Graph(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        adj.get(from).add(new Edge(from, to, weight));
    }

    public List<List<Edge>> getAdjList() {
        return adj;
    }

    public int size() {
        return n;
    }
}
