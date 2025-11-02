package graph;

import java.util.*;

public class TarjanSCC {
    private int time;
    private List<List<Integer>> components;
    private int[] ids, low;
    private boolean[] onStack;
    private Deque<Integer> stack;

    public List<List<Integer>> findSCC(Graph g) {
        int n = g.size();
        time = 0;
        components = new ArrayList<>();
        ids = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();

        Arrays.fill(ids, -1);

        for (int i = 0; i < n; i++) {
            if (ids[i] == -1)
                dfs(i, g.getAdjList());
        }

        return components;
    }

    private void dfs(int at, List<List<Edge>> adj) {
        ids[at] = low[at] = time++;
        stack.push(at);
        onStack[at] = true;

        for (Edge e : adj.get(at)) {
            if (ids[e.to] == -1) {
                dfs(e.to, adj);
                low[at] = Math.min(low[at], low[e.to]);
            } else if (onStack[e.to]) {
                low[at] = Math.min(low[at], ids[e.to]);
            }
        }

        if (ids[at] == low[at]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                comp.add(node);
                if (node == at) break;
            }
            components.add(comp);
        }
    }
}
