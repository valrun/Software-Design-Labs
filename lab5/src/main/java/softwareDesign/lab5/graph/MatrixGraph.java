package softwareDesign.lab5.graph;

import javafx.util.Pair;
import softwareDesign.lab5.drawingGraph.DrawingApi;

import java.util.*;

public class MatrixGraph extends Graph {
    private final Map<Integer, List<Integer>> edges = new HashMap<>();

    public MatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    @Override
    public void addEdge(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (!edges.containsKey(a)) {
            edges.put(a, new ArrayList<>());
        }
        edges.get(a).add(b);
    }

    @Override
    public void drawGraph() {
        drawingApi.initNodes(n);
        edges.forEach((a, list) -> {
            list.forEach(b -> drawingApi.initEdge(a, b));
        });

        drawingApi.draw();
    }
}
