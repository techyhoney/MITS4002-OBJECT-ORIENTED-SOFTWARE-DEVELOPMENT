
package draggingpointsonacircle;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DraggingPointsOnACircle extends Application {
    
     @Override
  public void start(Stage beginning) {
    final double width = 500.0;
    final double height = 500.0;
    final double radius = 200.0;
   
    // Initialising circle object with width, height, radius
    Circle[] points = new Circle[3];
    Circle circle = new Circle(width / 2, height / 2, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    Pane pane = new Pane();
    pane.getChildren().add(circle);
    
    Line[] lines = new Line[3];
    Text[] flags = new Text[3];
    
    // Creating 3 small circles on circumference of circle created above
    Circle[] newPoints = DrawTriangle.drawPoints(radius,circle,flags,lines,points);

    // creating triangle inside circle
    Line [] newLines = DrawTriangle.drawFunction(points,lines);

    // displaying angles using Text objects
    for (int i = 0; i < 3; i++) {
      Text text = new Text();
      text.xProperty().bind(newPoints[i].centerXProperty());
      text.yProperty().bind(newPoints[i].centerYProperty().subtract(radius / 20));
      flags[i] = text;
    }

    pane.getChildren().addAll(newPoints[0], newPoints[1], newPoints[2],
      newLines[0], newLines[1], newLines[2], flags[0], flags[1], flags[2]);

    Scene scene = new Scene(pane, width, height);
    beginning.setTitle("Dragging points on a Circle");
    beginning.setScene(scene);
    beginning.show();
  }
// function for computing angles
  public static double getAngle(Line x, Line y, Line z) {
    double d1 = distance(x);
    double d2 = distance(y);
    double d3 = distance(z);
    return Math.toDegrees(Math.acos((d1 * d1 - d2 * d2 - d3 * d3) / (-2 * d2 * d3)));
  }
// function for computing distance
  public static double distance(Line line) {
    double x1 = line.getStartX();
    double y1 = line.getStartY();
    double x2 = line.getEndX();
    double y2 = line.getEndY();
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  public static void main(String[] args) {
    launch(args);
  }
    
}
