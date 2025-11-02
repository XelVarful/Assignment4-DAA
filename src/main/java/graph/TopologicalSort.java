package graph;

import java.util.*;

public class TopologicalSort {
    public List<Integer> sort(Graph g) {
        int n = g.size();
        int[] indeg = new int[n];
        for (int u = 0; u < n; u++)
            for (Edge e : g.getAdjList().get(u))
                indeg[e.to]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indeg[i] == 0) q.add(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (Edge e : g.getAdjList().get(u)) {
                indeg[e.to]--;
                if (indeg[e.to] == 0) q.add(e.to);
            }
        }
        return order;
    }
}
