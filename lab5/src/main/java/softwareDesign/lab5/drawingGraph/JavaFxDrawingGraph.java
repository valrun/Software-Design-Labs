package softwareDesign.lab5.drawingGraph;

import javafx.application.Application;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static softwareDesign.lab5.drawingGraph.Utils.*;

/**
 * @author valrun
 */
public class JavaFxDrawingGraph implements DrawingApi {
    private int n = 0;
    private final List<Pair<Integer, Integer>> edges = new ArrayList<>();

    public long getDrawingAreaWidth() {
        return width;
    }

    public long getDrawingAreaHeight() {
        return height;
    }

    public void initNodes(int n) {
        this.n = n;
    }

    public void initEdge(int a, int b) {
        edges.add(new Pair<>(a, b));
    }

    public void draw() {
        JavaFxDrawingGraphApp.init(n, edges);
        Application.launch(JavaFxDrawingGraphApp.class);
    }

}

