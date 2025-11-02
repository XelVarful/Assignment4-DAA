package graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String dataPath = "data/small1.json";
        if (args.length > 0) dataPath = args[0];

        System.out.println("Loading graph from: " + dataPath);

        // Load the graph from JSON
        Graph g = new GraphLoader().load(dataPath);
        System.out.println("Graph loaded: " + g.size() + " nodes");

        // 1. Strongly Connected Components (Tarjan's algorithm)
        Metrics.startTimer();
        TarjanSCC scc = new TarjanSCC();
        List<List<Integer>> comps = scc.findSCC(g);
        Metrics.stopTimer();
        System.out.println("SCC count: " + comps.size());
        System.out.println("SCCs: " + comps);
        System.out.println("TarjanSCC time: " + Metrics.getElapsedMillis() + " ms");

        // 2. Build condensation graph (each SCC becomes a node)
        Metrics.startTimer();
        CondensationGraph cond = new CondensationGraph();
        Graph dag = cond.build(g, comps);
        Metrics.stopTimer();
        System.out.println("Condensed DAG size: " + dag.size());
        System.out.println("Condensation time: " + Metrics.getElapsedMillis() + " ms");

        // 3. Perform topological sort
        Metrics.startTimer();
        TopologicalSort topo = new TopologicalSort();
        List<Integer> order = topo.sort(dag);
        Metrics.stopTimer();
        System.out.println("Topological order: " + order);
        System.out.println("Topological sort time: " + Metrics.getElapsedMillis() + " ms");

        // 4. Compute shortest and longest paths from node 0
        if (dag.size() > 0) {
            int src = 0;
            DAGPaths paths = new DAGPaths();

            var shortest = paths.shortestPath(dag, src);
            var longest = paths.longestPath(dag, src);

            System.out.println("Shortest distances from " + src + ": " + shortest);
            System.out.println("Longest distances from " + src + ": " + longest);
        } else {
            System.out.println("Condensed DAG has 0 nodes. Skipping path computations.");
        }

        System.out.println("Execution completed.");
    }
}
