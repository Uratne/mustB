package drawingapp;

import java.awt.*;

/**
 * Creates a cross with a bounding rectangle.
 * Child class of figure.
 * @author yourName
 */
public class Cross extends Figure {

    public Cross(Point position, Dimension size, Color color) {
        super(position, size, "Cross", color);
    }
}
