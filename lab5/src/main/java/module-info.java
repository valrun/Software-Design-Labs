module softwareDesign.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens softwareDesign.lab5 to javafx.fxml;
    exports softwareDesign.lab5;
    exports softwareDesign.lab5.drawingGraph;
    opens softwareDesign.lab5.drawingGraph to javafx.fxml;
    exports softwareDesign.lab5.graph;
    opens softwareDesign.lab5.graph to javafx.fxml;
}