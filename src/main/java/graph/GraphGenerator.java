package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;

public class GraphGenerator {
    public Graph generate(int n, boolean cyclic, boolean dense) {
        Graph g = new Graph(n);
        Random rand = new Random();
        int edges = dense ? n * 3 : n * 1; // multiply to create more edges for denser graphs
        for (int i = 0; i < edges; i++) {
            int u = rand.nextInt(n);
            int v = rand.nextInt(n);
            if (u == v) continue;
            g.addEdge(u, v, rand.nextInt(9) + 1);
            if (cyclic && rand.nextDouble() < 0.15) {
                // add reverse to introduce cycles
                g.addEdge(v, u, rand.nextInt(9) + 1);
            }
        }
        return g;
    }

    public void save(Graph g, String filename) {
        JsonObject json = new JsonObject();
        json.addProperty("directed", true);
        json.addProperty("n", g.size());

        JsonArray edges = new JsonArray();
        for (int u = 0; u < g.size(); u++) {
            for (Edge e : g.getAdjList().get(u)) {
                JsonObject obj = new JsonObject();
                obj.addProperty("u", e.from);
                obj.addProperty("v", e.to);
                obj.addProperty("w", e.weight);
                edges.add(obj);
            }
        }
        json.add("edges", edges);

        try (FileWriter fw = new FileWriter(filename)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(json, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
