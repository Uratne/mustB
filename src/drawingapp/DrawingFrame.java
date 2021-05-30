package drawingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class creates the frame for the Swing program. On this frame we will build our
 * drawing program. This frame consists of two panels. 1. control panel | 2. drawing panel
 * @see ControlPanel
 * @see DrawingPanel
 * @author yourName
 */
public class DrawingFrame extends JFrame implements Constants {

    private final DrawingPanel drawingPanel = new DrawingPanel();
    FigurePanel figurePanel;
    ActionPanel actionPanel;
    FigureDataPanel figureDataPanel;
    CheckBoxPanel checkBoxPanel;
    ControlPanel controlPanel;
    
    public DrawingFrame() {
        
        //Initialisation code for the JFrame layout
        this.getContentPane().setLayout(new BorderLayout());

        //constructors
        figureDataPanel = new FigureDataPanel();
        figurePanel = new FigurePanel(drawingPanel, actionPanel, figureDataPanel);
        actionPanel = new ActionPanel(drawingPanel, figurePanel);
        checkBoxPanel = new CheckBoxPanel(drawingPanel);
        
        figurePanel.setActionPanel(actionPanel);

        controlPanel = new ControlPanel(figurePanel, actionPanel, figureDataPanel, checkBoxPanel);

         /*
          Mouse Listener for the drawing panel to lock and unlock figure panel and action panel
          during dynamic actions such as drawing a line or moving a figure so the user cannot enable another option.
          Such as while drawing a line select circle as final click of line should end the line. Not draw a circle.
         */
        class drawingActionHandler extends MouseAdapter {
            public void mouseClicked(MouseEvent e){
                if(figurePanel.getSelectedItem().equals("Line")) {
                    if (drawingPanel.getDrawingLine()) {
                        figurePanel.lockFigurePanel();
                        actionPanel.lockActionPanel();
                    } else {
                        figurePanel.unlockFigurePanel();
                        actionPanel.unlockActionPanel();
                    }
                }  
                if (actionPanel.getSelectedItem().equals("Move")){
                    if (drawingPanel.getMoving()) {
                        figurePanel.lockFigurePanel();
                        actionPanel.lockActionPanel();
                    } else {
                        figurePanel.unlockFigurePanel();
                        actionPanel.unlockActionPanel();
                    }
                }

                actionPanel.setSelectedIndex(0);
            }
        }

        drawingPanel.addMouseListener(new drawingActionHandler());

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
