package softwareDesign.lab5.drawingGraph;

import javafx.util.Pair;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.*;
import static softwareDesign.lab5.drawingGraph.Utils.*;

/**
 * @author valrun
 */
public class AwtDrawingGraph extends Frame implements DrawingApi {
    private int n = 0;
    private final List<Pair<Integer, Integer>> edges = new ArrayList<>();

    private void drawNodes(Graphics2D ga) {
        ga.setPaint(Color.DARK_GRAY);
        float d_ = 2 * nodeCircleR;

        for (int i = 1; i <= n; i++) {
            float x_ = (float) (mainCircleX + mainCircleR * cos(i * 2 * PI / n));
            float y_ = (float) (mainCircleY + mainCircleR * sin(i * 2 * PI / n));
            ga.fill(new Ellipse2D.Float(x_ - nodeCircleR, y_ - nodeCircleR, d_, d_));
        }
    }

    private void drawEdge(Graphics2D ga, int a, int b) {
        a = n - a + 1;
        b = n - b + 1;

        ga.setPaint(Color.BLACK);

        float x_a = (float) (mainCircleX + mainCircleR * cos(a * 2 * PI / n));
        float y_a = (float) (mainCircleY + mainCircleR * sin(a * 2 * PI / n));

        float x_b = (float) (mainCircleX + mainCircleR * cos(b * 2 * PI / n));
        float y_b = (float) (mainCircleY + mainCircleR * sin(b * 2 * PI / n));

        ga.draw(new Line2D.Double(x_a, y_a, x_b, y_b));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D) g;

        if (n > 0) {
            edges.forEach(p -> drawEdge(ga, p.getKey(), p.getValue()));
            drawNodes(ga);
        }
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void initNodes(int n) {
        this.n = n;
    }

    @Override
    public void initEdge(int a, int b) {
        edges.add(new Pair<>(a, b));
    }

    @Override
    public void draw() {
        Frame frame = this;
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize((int) width, (int) height);
        frame.setVisible(true);
    }
}
