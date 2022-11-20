package softwareDesign.lab5.graph;

import softwareDesign.lab5.drawingGraph.DrawingApi;

public abstract class Graph {
    /**
     * Bridge to drawing api
     */
    protected DrawingApi drawingApi;
    protected int n = 0;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public void addNode(int n) {
        this.n += n;
    }

    public abstract void addEdge(int a, int b);

    public abstract void drawGraph();
}
