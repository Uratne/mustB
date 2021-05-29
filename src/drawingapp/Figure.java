package drawingapp;

import javax.swing.*;
import java.awt.*;

public class Figure extends JComponent {

    Point position;
    Dimension size;
    String shape;
    Color color;

    public Figure(Point position, Dimension size, String shape, Color color) {
        this.position = position;
        this.size = size;
        this.shape = shape;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public Dimension getDimension() {
        return size;
    }

    public String getShape() {
        return shape;
    }

    public Color getColor() { return color; }

    public void setDimension(Dimension size) {
        this.size = size;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
