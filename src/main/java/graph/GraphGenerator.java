package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;

/**
 * GraphGenerator — utility for generating random directed graphs of different sizes and densities.
 * Automatically saves graphs as JSON files into /data folder.
 */
public class GraphGenerator {

    // === Core generator ===
    public Graph generate(int n, boolean cyclic, boolean dense) {
        Graph g = new Graph(n);
        Random rand = new Random();
        int edges = dense ? n * 3 : n; // more edges = denser graph

        for (int i = 0; i < edges; i++) {
            int u = rand.nextInt(n);
            int v = rand.nextInt(n);
            if (u == v) continue; // no self-loops

            g.addEdge(u, v, rand.nextInt(9) + 1);

            if (cyclic && rand.nextDouble() < 0.15) {
                // add a reverse edge to create cycles sometimes
                g.addEdge(v, u, rand.nextInt(9) + 1);
            }
        }

        return g;
    }

    // === Save graph to JSON ===
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
            System.out.println("✅ Saved graph: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // === Generate and save one dataset ===
    public void generateAndSave(int n, double density, boolean cyclic, String filename) {
        Graph g = generate(n, cyclic, density > 0.5);
        save(g, filename);
    }

    // === Generate all datasets (9 total) ===
    public static void generateAllDatasets() {
        GraphGenerator gg = new GraphGenerator();

        // Small (6–10)
        gg.generateAndSave(6, 0.3, false, "data/small1.json");
        gg.generateAndSave(7, 0.5, true, "data/small2.json");
        gg.generateAndSave(9, 0.7, true, "data/small3.json");

        // Medium (10–20)
        gg.generateAndSave(12, 0.4, false, "data/medium1.json");
        gg.generateAndSave(16, 0.6, true, "data/medium2.json");
        gg.generateAndSave(18, 0.8, true, "data/medium3.json");

        // Large (20–50)
        gg.generateAndSave(25, 0.3, false, "data/large1.json");
        gg.generateAndSave(35, 0.6, true, "data/large2.json");
        gg.generateAndSave(45, 0.9, true, "data/large3.json");

        System.out.println("🎉 All datasets generated successfully!");
    }
}
