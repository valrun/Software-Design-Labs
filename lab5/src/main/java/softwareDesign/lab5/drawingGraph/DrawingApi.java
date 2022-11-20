package softwareDesign.lab5.drawingGraph;

public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();

    void initNodes(int n);
    void initEdge(int a, int b);

    void draw();
}
