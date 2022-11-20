package softwareDesign.lab5;

import softwareDesign.lab5.drawingGraph.AwtDrawingGraph;
import softwareDesign.lab5.drawingGraph.DrawingApi;
import softwareDesign.lab5.drawingGraph.JavaFxDrawingGraph;
import softwareDesign.lab5.graph.EdgeGraph;
import softwareDesign.lab5.graph.Graph;
import softwareDesign.lab5.graph.MatrixGraph;

import java.util.Objects;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length != 2
                || !Set.of("matrix", "edge").contains(args[0])
                || !Set.of("javafx", "awt").contains(args[1])) {
            System.out.println("Введите 2 аргумента:\n"
            + "\"matrix\" или \"edge\"\n"
            + "\"javafx\" или \"awt\"");
            return;
        }


        DrawingApi api = Objects.equals(args[1], "javafx") ? new JavaFxDrawingGraph() : new AwtDrawingGraph();
        Graph graph = Objects.equals(args[1], "matrix") ? new MatrixGraph(api) : new EdgeGraph(api);

        graph.addNode(5);
        graph.addEdge(0, 4);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        graph.drawGraph();
    }

    public static void main1() {
        main(new String[]{"matrix", "javafx"});
    }
}
