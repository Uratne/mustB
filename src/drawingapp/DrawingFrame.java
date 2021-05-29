package drawingapp;

import javax.swing.*;
import java.awt.*;

public class DrawingFrame extends JFrame implements Constants {

    private final DrawingPanel drawingPanel = new DrawingPanel();
    FigurePanel figurePanel;
    ControlPanel controlPanel;
    
    public DrawingFrame() {
        
        //Initialisation code for the JFrame layout
        this.getContentPane().setLayout(new BorderLayout());

        //constructors
        figurePanel = new FigurePanel(drawingPanel);


        controlPanel = new ControlPanel(figurePanel);


        /*
          Initialisation code for the JFrame.
         */
        this.getContentPane().add(controlPanel, "North"); //sets control panel as the North panel of the Border layout.
        this.getContentPane().add(drawingPanel, "Center");//set drawing panel as the Center panel of the Border layout.

        this.setPreferredSize(new Dimension(450, 500));
        this.setLocationRelativeTo(null);
        this.setTitle("Drawing Application");
        this.pack();
        this.setResizable(false);
        this.setVisible(true); // should be last line of the MyFrame constructor
    }

}
