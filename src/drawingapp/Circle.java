package drawingapp;

import java.awt.*;

/**
 * Creates a circle with bounding rectangle.
 * Child class of figure.
 * @author yourName
 */
public class Circle extends Figure {

    public Circle(Point position, Dimension size, Color color) {
        super(position, size, "Circle", color);
    }
}
