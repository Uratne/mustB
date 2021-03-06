package drawingapp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lets User change the figure to be drawn on the next mouse click input.
 * Figures are Circle, Square, Cross and Line.
 * This Combo Box is locked during drawing lines and moving figures.
 * @author yourName
 */
public class FigurePanel extends JPanel implements Constants {

    JComboBox figures;
    ActionPanel actionPanel;

    FigurePanel(DrawingPanel drawingPanel, ActionPanel actionPanel, FigureDataPanel figureDataPanel){
        this.setLayout(new GridBagLayout());
        this.actionPanel = actionPanel;

        TitledBorder figurePanelBorder = BorderFactory.createTitledBorder("Figure Types");
        figurePanelBorder.setTitleFont(arialBold12);

        this.setBorder(figurePanelBorder);

        figures = new JComboBox(figureOptions);
        figures.setBounds(50,50,90,5);
        figures.setBackground(Color.blue.darker());
        figures.setForeground(Color.white);

        ActionListener jComBoxActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jComBoxData = (String) figures.getSelectedItem();
                String actionBoxData = (String) getActionPanel().getSelectedItem();
                System.out.println("Choice changed to " + jComBoxData);
                figureDataPanel.setText(jComBoxData);
                drawingPanel.setFigure(jComBoxData);
                if (jComBoxData.equals("Line") && (actionBoxData.equals("Enlarge") || actionBoxData.equals("Shrink"))) {
                    getActionPanel().setSelectedIndex(0);
                }
            }
        };

        figures.addActionListener(jComBoxActionListener);

        this.add(figures, new GridBagConstraints(0, 0, 4, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 40));
    }

    public Object getSelectedItem() {
        return this.figures.getSelectedItem();
    }

    public void setActionPanel(ActionPanel actionPanel) {
        this.actionPanel = actionPanel;
    }

    public ActionPanel getActionPanel() {
        return actionPanel;
    }
    
    public void lockFigurePanel() {
        this.figures.setEnabled(false);
    }

    public void unlockFigurePanel() {
        this.figures.setEnabled(true);
    }
}
