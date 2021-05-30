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

    DrawingPanel(){
        this.figure = "Circle";
        this.action = "None";
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

            int width = (int) figure.getDimension().getWidth();
            int height = (int) figure.getDimension().getHeight();
            String shape = figure.getShape();
            draw(g, shape, x, y, width, height);
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
                    }
                    break;
                case "Enlarge":
                    enlarge(e.getX(), e.getY());
                    break;
                case "Shrink":
                    shrink(e.getX(), e.getY());
                    break;
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

            int newMiddle = (int) figure.getDimension().getWidth() / 2;
            int figureX = Math.abs((int) figure.getPosition().getX() + newMiddle - x);
            int figureY = Math.abs((int) figure.getPosition().getY() + newMiddle - y);

            if (figureX <= newMiddle && figureY <= newMiddle) {
                index = i;
                break;
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

}
