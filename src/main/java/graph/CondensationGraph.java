package graph;

import java.util.*;

public class CondensationGraph {

    public Graph build(Graph g, List<List<Integer>> sccs) {
        int compCount = sccs.size();
        Graph dag = new Graph(compCount);

        Map<Integer, Integer> nodeToComp = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++) {
            for (int node : sccs.get(i))
                nodeToComp.put(node, i);
        }

        // avoid duplicate parallel edges by using a set of pairs (cu,cv) -> choose first weight
        Map<Long, Integer> seen = new HashMap<>();

        for (int u = 0; u < g.size(); u++) {
            for (Edge e : g.getAdjList().get(u)) {
                int cu = nodeToComp.get(u);
                int cv = nodeToComp.get(e.to);
                if (cu != cv) {
                    long key = (((long) cu) << 32) | (cv & 0xffffffffL);
                    if (!seen.containsKey(key)) {
                        dag.addEdge(cu, cv, e.weight);
                        seen.put(key, e.weight);
                    }
                }
            }
        }

        return dag;
    }
}
