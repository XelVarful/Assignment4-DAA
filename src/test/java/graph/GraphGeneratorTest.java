package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphGeneratorTest {

    @Test
    void testGraphGeneration() {
        GraphGenerator gen = new GraphGenerator();
        Graph g = gen.generate(8, true, false);

        assertNotNull(g);
        assertTrue(g.size() > 0);
    }
}
