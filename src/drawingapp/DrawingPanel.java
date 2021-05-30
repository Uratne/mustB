package drawingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Drawing panel is the canvas for the user to draw a composite of figures using options from the control panel.
 * Users can draw circles, squares, crosses and lines.
 * Users can perform actions on previously drawn figures such as delete, enlarge, shrink and move.
 * For lines enlarge and shrink is not allowed and control panel will not let the user have this
 * combination of choices.
 * Users can select 4 different colors, Red, Blue, Green and Black.
 * Actions are performed on figures depending on the last created figure as the top most layer and
 * it will remove
 * the top most layer that corresponds to the clicked point.
 * @see ControlPanel
 * @author yourName
 */
public class DrawingPanel extends JPanel implements Constants {
    
    List<Figure> figureList = new ArrayList<>();
    String figure, action;
    Color selectedColor;
    Boolean drawingLine, moving;
    int movingFigure;

    DrawingPanel(){
        this.figure = "Circle";
        this.action = "None";
        this.drawingLine = false;
        this.moving = false;
        this.movingFigure = 0;
        this.selectedColor = Color.red;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(450, 400));
        this.setBackground(Color.white);
        this.addMouseListener(new ActionHandler());
        this.addMouseMotionListener(new ActionHandler());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Figure figure: figureList) {
            g.setColor(figure.getColor());
            int x = (int) figure.getPosition().getX();
            int y = (int) figure.getPosition().getY();
            if (figure.getShape().equals("Line")){
                Line line = (Line) figure;
                int endX = (int) line.getEndPosition().getX();
                int endY = (int) line.getEndPosition().getY();
                g.drawLine(x,y,endX, endY);
            } else {
                int width = (int) figure.getDimension().getWidth();
                int height = (int) figure.getDimension().getHeight();
                String shape = figure.getShape();
                draw(g, shape, x, y, width, height);
            }
        }
    }

    public void setAction(String actionJComBoxData) {
        action = actionJComBoxData;
    }

    /**
     * Action handler for the drawing panel.
     * Mouse clicks and mouse movements are tracked.
     */
    private class ActionHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            System.out.println("Mouse pressed at ("+e.getX()+","+e.getY() +")");
            switch (action) {
                case "Delete":
                    remove(e.getX(), e.getY());
                    break;
                case "None":
                    if ("Square".equals(figure)) {
                        addRect(e.getX(), e.getY(), width, height, getSelectedColor());
                    } else if ("Circle".equals(figure)) {
                        addCircle(e.getX(), e.getY(), width, height, getSelectedColor());
                    } else if ("Cross".equals(figure)) {
                        addCross(e.getX(), e.getY(), width, height, getSelectedColor());
                    } else if ("Line".equals(figure)) {
                        if (drawingLine) {
                            drawLine(e.getX(), e.getY());
                            drawingLine = false;
                        } else {
                            startLine(e.getX(), e.getY(), getSelectedColor());
                            drawingLine = true;
                        }

                    }
                    break;
                case "Enlarge":
                    enlarge(e.getX(), e.getY());
                    break;
                case "Shrink":
                    shrink(e.getX(), e.getY());
                    break;
                case "Move":
                    if (!moving) {
                        movingFigure = getMovingFigure(e.getX(), e.getY());
                        if (movingFigure != -1) moving = true;
                    } else {
                        moving = false;
                    }
                    break;
            }
        }

        /**
         * mouse movements are used for draiwing lines while on figure options: Line
         * also used when the action option is set to Move, to move figures dynamically.
         */
        public void mouseMoved(MouseEvent e) {
            if(drawingLine) {
                drawLine(e.getX(), e.getY());
            } else if (moving) {
                moveFigure(movingFigure, e.getX(), e.getY());
            }
        }
    }

    /**
     * Adds a new square to the drawing panel.
     * @param x is the x value of the mouse click on the canvas.
     * @param y is the y value of the mouse click on the canvas.
     * @param width default is set to 50 pixels.
     * @param height default is set to 50 pixels.
     * @param color color of the square.
     */
    public void addRect(int x, int y, int width, int height, Color color){
        Point point = new Point(x,y);
        Dimension dimension = new Dimension(width, height);

        Rect rectangle = new Rect(point, dimension, color);
        figureList.add(rectangle);
        this.repaint();
        
    }

    /**
     * Adds a new circle to the drawing panel.
     * @param x is the x value of the mouse click on the canvas.
     * @param y is the y value of the mouse click on the canvas.
     * @param width default is set to 50 pixels.
     * @param height default is set to 50 pixels.
     * @param color color of the circle.
     */
    public void addCircle(int x, int y, int width, int height, Color color){
        Point point = new Point(x, y);
        Dimension dimension = new Dimension(width, height);

        Circle circle = new Circle(point, dimension, color);
        figureList.add(circle);
        this.repaint();
    }

    /**
     * Adds a new cross to the drawing panel.
     * @param x is the x value of the mouse click on the canvas.
     * @param y is the y value of the mouse click on the canvas.
     * @param width default is set to 50 pixels.
     * @param height default is set to 50 pixels.
     * @param color color of the cross.
     */
    public void addCross(int x,int y, int width, int height, Color color){
        Point point = new Point(x, y);
        Dimension dimension = new Dimension(width, height);

        Cross cross = new Cross(point, dimension, color);
        figureList.add(cross);
        this.repaint();
    }

    public void setFigure(String figure){
        this.figure = figure;
    }

    /**
     * Draws figures on the canvas case accordingly.
     * @param g graphics passed from paint component
     * @param shape defines the shape of the figure Eg: Circle
     * @param x x coordinates to the point to draw.
     * @param y y coordinates to the point to draw.
     * @param width default is set to 50
     * @param height default is set to 50
     */
    private void draw(Graphics g, String shape, int x, int y, int width, int height){
        switch (shape) {
            case "Circle" -> g.drawOval(x, y, width, height);
            case "Square" -> g.drawRect(x, y, width, height);
            case "Cross" -> {
                g.drawLine(x, y, x + width, y + height);
                g.drawLine(x + width, y, x, y + height);
            }
        }
    }

    /**
     * Finds the figure that the action should be performed on
     * @param x x location of the click input
     * @param y y location of the click input
     * @return is the index of the figure from the figureList
     */
    private int findFigure(int x, int y){
        int index = -1;
        for ( int i = figureList.size()-1; i >= 0; i--){
            Figure figure = figureList.get(i);
            if(figure.getShape().equals("Line")){
                Line line = (Line) figure;
                boolean nearLine = isNearLine(line, x, y);
                if(nearLine) {
                    index = i;
                    break;
                }
            } else {
                int newMiddle = (int) figure.getDimension().getWidth() / 2;
                int figureX = Math.abs((int) figure.getPosition().getX() + newMiddle - x);
                int figureY = Math.abs((int) figure.getPosition().getY() + newMiddle - y);

                if (figureX <= newMiddle && figureY <= newMiddle) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    /**
     * deletes a figure depending on the mouse click coordinates
     * @param x x coordinates of the mouse click input
     * @param y y coordinates of the mouse click input
     */
    public void remove(int x, int y){
        int index = findFigure(x, y);

        if(index != -1){
            figureList.remove(index);
        }

        this.repaint();
    }

    /**
     * Enlarges a figure by %50 depending on the current size of the figure.
     * @param x x coordinates of the mouse click input
     * @param y y coordinates of the mouse click input
     */
    private void enlarge(int x, int y){
        int index = findFigure(x, y);
        
        if(index != -1){
            Figure figure = figureList.get(index);
            int newWidth = (int) ((int) figure.getDimension().getWidth() * 1.5);
            int newHeight = (int) ((int) figure.getDimension().getWidth() * 1.5);

            figure.setDimension(new Dimension(newWidth, newHeight));
        }

        this.repaint();
    }

    /**
     * Shrinks a figure by %10 depending on the current size of the figure.
     * @param x x coordinates of the mouse click input
     * @param y y coordinates of the mouse click input
     */
    public void shrink(int x, int y){
        int index = findFigure(x, y);

        if(index != -1){
            Figure figure = figureList.get(index);
            int newWidth = (int) ((int) figure.getDimension().getWidth() * 0.9);
            int newHeight = (int) ((int) figure.getDimension().getWidth() * 0.9);

            figure.setDimension(new Dimension(newWidth, newHeight));
        }

        this.repaint();
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * Creates figure line.
     * To create a line two mouse click inputs on the canvas is required. This will take the first mouse click
     * input and initialise a line. After initialising a line control panel is locked till the
     * second finalising click. Locking is done in the drawing frame
     * @see DrawingFrame
     * @param x x coordinates for initialising a line.
     * @param y y coordinates for initialising a line.
     * @param color color of the line.
     */
    public void startLine(int x, int y, Color color) {
        Point point = new Point(x, y);
        Dimension dimension = new Dimension(0,0);

        Line line = new Line(point, point, dimension, color);
        figureList.add(line);
    }

    /**
     * Ends a figure line. startLine() need to be invoked before this is invoked. And it should be
     * invoked right after startLine()
     * @param x x coordinates for the initialised lines end point.
     * @param y y coordinates for the initialised lines end point.
     */
    public void drawLine(int x, int y) {
        Point point = new Point(x, y);
        Line line = (Line) figureList.get(figureList.size() - 1);

        line.setEndPosition(point);

        this.repaint();
    }

    /**
     * Since lines does not have a bounding rectangle similar to that of other figures
     * to find the smallest rectangle such that it bounds the line with 1 pixel distance this method is used.
     * we will check the distance from a given point from mouse click input to the line along the x-axis and
     * the y-axis such that even if one of them is less than 1 pixel method will return as true;
     * <img src="./doc-files/figure.jpg">
     * @param line line in question that needs to be checked for the distance relative to the point
     * @param m x coordinates for the point which the line is relative to
     * @param n y coordinates for the point which the line is relative to
     * @return is a boolean value whether or not the line is close to the given point along x-axis or y-axis
     */
    public boolean isNearLine(Line line, int m, int n){
        double a1 = line.getPosition().getX();
        double b1 = line.getPosition().getY();

        double a2 = line.getEndPosition().getX();
        double b2 = line.getEndPosition().getY();

        double A = (a1 - a2)/(b1 - b2);
        double Xn = A*n - A*b2 + a2;
        double Ym = m/A - 1/A*a2 + b2;

        boolean xNearLine = Math.abs(Xn - m) <= 1;
        boolean yNearLine = Math.abs(Ym - n) <= 1;

        return xNearLine || yNearLine;
    }

    public Boolean getMoving() {
        return moving;
    }

    private int getMovingFigure(int x, int y){
        return findFigure(x, y);
    }

    /**
     * This will move a figure to a given point.
     * @param index index of the figure to be moved in figureList.
     * @param x x coordinate of the point which you want the figure to be moved
     * @param y y coordinate of the point which you want the figure to be moved.
     */
    public void moveFigure(int index,int x, int y){

        if (index != -1) {
            Figure figure = figureList.get(index);

            if(figure.getShape().equals("Line")){
                Line line = (Line) figure;
                int endX = (int) line.getEndPosition().getX() + (x - (int) figure.getPosition().getX());
                int endY = (int) line.getEndPosition().getY() + (y - (int) figure.getPosition().getY());
                
                Point lineEndPoint = new Point(endX, endY);
                line.setEndPosition(lineEndPoint);
            }
            Point point = new Point(x, y);
            figure.setPosition(point);

            this.repaint();
        } else {
            moving = false;
        }
    }

    public Boolean getDrawingLine() {
        return drawingLine;
    }
}
