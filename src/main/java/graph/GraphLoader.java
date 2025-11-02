package graph;

import com.google.gson.*;
import java.io.FileReader;

public class GraphLoader {
    public Graph load(String path) {
        try (FileReader reader = new FileReader(path)) {
            JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
            int n = obj.get("n").getAsInt();
            Graph g = new Graph(n);
            JsonArray edges = obj.getAsJsonArray("edges");
            for (JsonElement e : edges) {
                JsonObject ed = e.getAsJsonObject();
                g.addEdge(ed.get("u").getAsInt(),
                        ed.get("v").getAsInt(),
                        ed.get("w").getAsInt());
            }
            return g;
        } catch (Exception e) {
            throw new RuntimeException("Error loading graph: " + e.getMessage(), e);
        }
    }
}
