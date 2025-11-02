package graph;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {

    @Test
    void testSimpleSCC() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);

        TarjanSCC scc = new TarjanSCC();
        var comps = scc.findSCC(g);

        assertEquals(1, comps.size(), "All nodes should form one SCC");
        assertTrue(comps.get(0).containsAll(Arrays.asList(0, 1, 2)));
    }
}
