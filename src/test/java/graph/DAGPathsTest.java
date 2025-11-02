package graph;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGPathsTest {

    @Test
    void testShortestAndLongestPaths() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 4);
        g.addEdge(2, 3, 1);

        DAGPaths paths = new DAGPaths();
        var shortest = paths.shortestPath(g, 0);
        var longest = paths.longestPath(g, 0);

        assertEquals(5, shortest.get(3));
        assertEquals(6, longest.get(3));
    }
}
