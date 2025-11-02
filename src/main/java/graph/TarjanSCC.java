package graph;

import java.util.*;

public class TarjanSCC {
    private int time;
    private int[] disc;
    private int[] low;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private List<List<Integer>> result;

    public List<List<Integer>> findSCC(Graph g) {
        Metrics.startTimer();

        int n = g.size();
        time = 0;
        disc = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        result = new ArrayList<>();

        Arrays.fill(disc, -1);

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, g);
            }
        }

        Metrics.stopTimer();
        System.out.println("Tarjan SCC time: " + Metrics.getElapsedMillis() + " ms");
        return result;
    }

    private void dfs(int u, Graph g) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        onStack[u] = true;

        for (Edge e : g.getAdjList().get(u)) {
            int v = e.to;
            if (disc[v] == -1) {
                dfs(v, g);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // root of SCC
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                onStack[v] = false;
                component.add(v);
            } while (v != u);
            result.add(component);
        }
    }
}
