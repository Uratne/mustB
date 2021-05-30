package drawingapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

public class DrawingPanelTest {
    public DrawingPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * This test is to check the functionality of the drawing panel.
     * A figure is drawn, shrank and moved to check the functionality
     * @throws Exception InterruptedException
     * @author yourName
     */
    @Test
    public void drawCarTest() throws Exception {
        System.out.println("Draw car test");
        JFrame jFrame = new JFrame();

        jFrame.setSize(450,400);
        jFrame.setPreferredSize(new Dimension(450, 500));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Drawing Panel Test Application");
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        DrawingPanel drawingPanel = new DrawingPanel();
        jFrame.add(drawingPanel);

        try {
            drawingPanel.addCircle(30, 105, 50, 50, Color.black);
            Thread.sleep(200);
            drawingPanel.addCircle(180, 105, 50, 50, Color.black);
            Thread.sleep(200);

            drawingPanel.addRect(55, 80, 50, 50, Color.blue);
            Thread.sleep(200);
            drawingPanel.addRect(155, 80, 50, 50, Color.blue);
            Thread.sleep(200);
            drawingPanel.addRect(105, 80, 50, 50, Color.blue);
            Thread.sleep(200);
            drawingPanel.addRect(105, 30, 50, 50, Color.red);
            Thread.sleep(200);

            drawingPanel.addCross(55, 80, 50, 50, Color.green);
            Thread.sleep(200);
            drawingPanel.addCross(155, 80, 50, 50, Color.green);
            Thread.sleep(200);

            drawingPanel.startLine(5,130,Color.blue);
            drawingPanel.drawLine(55,130);
            Thread.sleep(200);
            drawingPanel.startLine(5,130,Color.blue);
            drawingPanel.drawLine(55,80);
            Thread.sleep(200);

            drawingPanel.addRect(110,85,50,50, Color.black);
            Thread.sleep(200);
            
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            drawingPanel.shrink(111,86);
            Thread.sleep(500);
            
            drawingPanel.moveFigure(10, 111,101);
            Thread.sleep(500);
            drawingPanel.moveFigure(10, 126,101);
            Thread.sleep(500);
            drawingPanel.moveFigure(10, 126,86);
            Thread.sleep(500);
            drawingPanel.moveFigure(10,110,85);
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
    }

    /**
     * This test is to check the figure selection methods functionalities correctness.
     * Also check remove methods functionalities correctness.
     * @throws Exception InterruptedException
     * @author yourName
     */
    @Test
    public void figureSelectionTest() throws Exception {

        System.out.println("figure selection test");
        System.out.println("Figures are created in following order:\n" +
                           "\t1.Red square\n" +
                           "\t2.Blue Square\n" +
                           "\t3.Green square\n" +
                           "\t4.Black square\n" +
                           "\t5.Black circle");
        JFrame jFrame = new JFrame();

        jFrame.setSize(450,400);
        jFrame.setPreferredSize(new Dimension(450, 500));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Drawing Panel Figure Selection Test Application");
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        DrawingPanel drawingPanel = new DrawingPanel();
        jFrame.add(drawingPanel);

        try {
            drawingPanel.addRect(55,55,50,50,Color.RED);
            Thread.sleep(500);
            drawingPanel.addRect(55,95,50,50,Color.blue);
            Thread.sleep(500);
            drawingPanel.addRect(95,95,50,50,Color.green);
            Thread.sleep(500);
            drawingPanel.addRect(95,55,50,50,Color.black);
            Thread.sleep(500);
            drawingPanel.addCircle(75,75,50,50,Color.black);
            Thread.sleep(500);
            
            for (int i = 0; i < 5; i++) {
                drawingPanel.remove(100,100);
                Thread.sleep(500);
            }
        } catch (InterruptedException ignore) {}
        
    }
}
