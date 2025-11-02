package graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String dataPath = "data/small1.json";
        if (args.length > 0) dataPath = args[0];

        System.out.println("Loading graph from: " + dataPath);
        Graph g = new GraphLoader().load(dataPath);

        System.out.println("Graph loaded: " + g.size() + " nodes");

        // SCC
        TarjanSCC scc = new TarjanSCC();
        List<List<Integer>> comps = scc.findSCC(g);
        System.out.println("SCC count: " + comps.size());
        System.out.println("SCCs: " + comps);

        // Condensation DAG
        CondensationGraph cond = new CondensationGraph();
        Graph dag = cond.build(g, comps);
        System.out.println("Condensed DAG size: " + dag.size());

        // Topological order
        TopologicalSort topo = new TopologicalSort();
        var order = topo.sort(dag);
        System.out.println("Topological Order on condensed DAG: " + order);

        // Paths (from component 0)
        if (dag.size() > 0) {
            int src = 0;
            DAGPaths paths = new DAGPaths();
            var shortest = paths.shortestPath(dag, src);
            var longest = paths.longestPath(dag, src);
            System.out.println("Shortest distances from " + src + ": " + shortest);
            System.out.println("Longest distances from " + src + ": " + longest);
        } else {
            System.out.println("Condensed DAG has 0 nodes, skipping path computations.");
        }
    }
}
