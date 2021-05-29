package drawingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel implements Constants {
    
    List<Figure> figureList = new ArrayList<>();
    String figure;
    Color selectedColor;

    DrawingPanel(){
        this.figure = "Circle";
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

    class ActionHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            System.out.println("Mouse pressed at ("+e.getX()+","+e.getY() +")");
            if ("Square".equals(figure)) {
                addRect(e.getX(), e.getY(), width, height, getSelectedColor());
            } else if ("Circle".equals(figure)) {
                addCircle(e.getX(), e.getY(), width, height, getSelectedColor());
            } else if ("Cross".equals(figure)) {
                addCross(e.getX(), e.getY(), width, height, getSelectedColor());
            }
        }
    }


    public void addRect(int x, int y, int width, int height, Color color){
        Point point = new Point(x,y);
        Dimension dimension = new Dimension(width, height);

        Rect rectangle = new Rect(point, dimension, color);
        figureList.add(rectangle);
        this.repaint();
        
    }

    public void addCircle(int x, int y, int width, int height, Color color){
        Point point = new Point(x, y);
        Dimension dimension = new Dimension(width, height);

        Circle circle = new Circle(point, dimension, color);
        figureList.add(circle);
        this.repaint();
    }

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


    public Color getSelectedColor() {
        return selectedColor;
    }


}
