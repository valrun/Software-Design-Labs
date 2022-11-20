package softwareDesign.lab5.drawingGraph;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;

import static java.lang.Math.*;
import static softwareDesign.lab5.drawingGraph.Utils.*;

public class JavaFxDrawingGraphApp extends Application {
    private static int n = 0;
    private static List<Pair<Integer, Integer>> edges = null;

    public static void init(int n, List<Pair<Integer, Integer>> edges) {
        JavaFxDrawingGraphApp.n = n;
        JavaFxDrawingGraphApp.edges = edges;
    }

    private void drawNodes(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        float d_ = 2 * nodeCircleR;

        for (int i = 1; i <= n; i++) {
            double x_ = mainCircleX + mainCircleR * cos(i * 2 * PI / n);
            double y_ = mainCircleY + mainCircleR * sin(i * 2 * PI / n);
            gc.fillOval(x_ - nodeCircleR, y_ - nodeCircleR, d_, d_);
        }
    }

    private void drawEdge(GraphicsContext gc, int a, int b) {
        a = n - a + 1;
        b = n - b + 1;

        gc.setFill(Color.BLACK);

        double x_a = mainCircleX + mainCircleR * cos(a * 2 * PI / n);
        double y_a = mainCircleY + mainCircleR * sin(a * 2 * PI / n);

        double x_b = mainCircleX + mainCircleR * cos(b * 2 * PI / n);
        double y_b = mainCircleY + mainCircleR * sin(b * 2 * PI / n);

        gc.strokeLine(x_a, y_a, x_b, y_b);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing graph");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.err.println(n);
        if (n > 0) {
            edges.forEach(p -> drawEdge(gc, p.getKey(), p.getValue()));
            drawNodes(gc);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
