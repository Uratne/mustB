package drawingapp;

import java.awt.*;

/**
 * Creates a line with the smallest bounding rectangle.
 * Child class of figure and have an extra variable, endPosition -> end Point of the line.
 * @author yourName
 */
public class Line extends Figure{
    Point endPosition;
    
    public Line(Point position, Point endPosition, Dimension size, Color color) {
        super(position, size, "Line", color);
        this.endPosition = endPosition;
    }

    private Dimension getLength(){
        double x = position.getX();
        double endX = endPosition.getX();
        
        double y = position.getY();
        double endY = position.getY();
        
        int length = (int) Math.sqrt(Math.pow((x-endX), 2) + Math.pow((y-endY), 2));
        
        return new Dimension(length, 1);
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(getLength());
    }

    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }

    public Point getEndPosition() {
        return endPosition;
    }
}
