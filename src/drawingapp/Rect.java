package drawingapp;

import java.awt.*;

/**
 * Creates a square with a bounding rectangle.
 * Child class of figure.
 * @author yourName
 */
public class Rect extends Figure {

    public Rect(Point position, Dimension size, Color color) {
        super(position, size, "Square", color);
    }

}
