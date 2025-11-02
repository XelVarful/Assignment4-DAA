package graph;

import java.util.*;

public class DAGPaths {

    public Map<Integer, Integer> shortestPath(Graph g, int src) {
        List<Integer> order = new TopologicalSort().sort(g);
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 0; i < g.size(); i++) dist.put(i, Integer.MAX_VALUE);
        dist.put(src, 0);

        for (int u : order) {
            if (dist.get(u) == Integer.MAX_VALUE) continue;
            for (Edge e : g.getAdjList().get(u)) {
                int nd = dist.get(u) + e.weight;
                if (nd < dist.get(e.to))
                    dist.put(e.to, nd);
            }
        }
        return dist;
    }

    public Map<Integer, Integer> longestPath(Graph g, int src) {
        List<Integer> order = new TopologicalSort().sort(g);
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 0; i < g.size(); i++) dist.put(i, Integer.MIN_VALUE);
        dist.put(src, 0);

        for (int u : order) {
            if (dist.get(u) == Integer.MIN_VALUE) continue;
            for (Edge e : g.getAdjList().get(u)) {
                int nd = dist.get(u) + e.weight;
                if (nd > dist.get(e.to))
                    dist.put(e.to, nd);
            }
        }
        return dist;
    }
}
