package softwareDesign.lab5.graph;

import javafx.util.Pair;
import softwareDesign.lab5.drawingGraph.DrawingApi;

import java.util.ArrayList;
import java.util.List;

public class EdgeGraph extends Graph {
    private final List<Pair<Integer, Integer>> edges = new ArrayList<>();

    public EdgeGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    @Override
    public void addEdge(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        edges.add(new Pair<>(a, b));
    }

    @Override
    public void drawGraph() {
        drawingApi.initNodes(n);
        edges.forEach(p -> drawingApi.initEdge(p.getKey(), p.getValue()));

        drawingApi.draw();
    }
}
