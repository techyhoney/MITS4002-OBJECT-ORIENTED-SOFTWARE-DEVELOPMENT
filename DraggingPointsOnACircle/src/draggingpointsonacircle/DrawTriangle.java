
package draggingpointsonacircle;

import static draggingpointsonacircle.DraggingPointsOnACircle.getAngle;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class DrawTriangle {
    // function to draw circle repersenting vertices of triangle
static Circle[] drawPoints (double radius, Circle circle, Text[]flags,Line[] lines, Circle[] points )
{
    for (int j = 0; j < 3; j++) {
      Circle point = new Circle(radius / 20);
      point.setFill(Color.RED);
      double randomAngle = Math.random() * 2 * Math.PI;
      double x = circle.getCenterX() + radius * Math.cos(randomAngle);
      double y = circle.getCenterY() + radius * Math.sin(randomAngle);
      point.setCenterX(x);
      point.setCenterY(y);
      // Dynamic changes on mouse drag
      point.setOnMouseDragged(e -> {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
          double angle = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
          Circle c = (Circle)e.getSource();
          c.setCenterX(circle.getCenterX() + radius * Math.cos(angle));
          c.setCenterY(circle.getCenterY() + radius * Math.sin(angle));
          angle = getAngle(lines[1], lines[2], lines[0]);
          flags[0].setText(String.format("%.2f", angle));      // setting precision 
          angle = getAngle(lines[2], lines[1], lines[0]);
          flags[1].setText(String.format("%.2f", angle));
          angle = getAngle(lines[0], lines[2], lines[1]);
          flags[2].setText(String.format("%.2f", angle));
        }
      });
      points[j] = point;
    }
    return points;
}
    // function to draw lines of triangle
static Line[] drawFunction(Circle [] points, Line [] lines)
{
    for (int i = 0; i < 3; i++) {
      int var = i == 2 ? 0 : i + 1;
      Line line = new Line();
      line.startXProperty().bind(points[i].centerXProperty());
      line.startYProperty().bind(points[i].centerYProperty());
      line.endXProperty().bind(points[var].centerXProperty());
      line.endYProperty().bind(points[var].centerYProperty());
      lines[i] = line;
    }
        return lines;
}

}
