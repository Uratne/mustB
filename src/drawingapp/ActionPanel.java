package drawingapp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Action Panel lets the user choose the action to be performed on a figure.
 * Top most figure on clicked position will be selected to perform the action.
 * Actions are none, delete, enlarge, shrink and move.
 * This Combo Box is locked during drawing a line and moving a figure.
 * @author yourName
 */
public class ActionPanel extends JPanel implements Constants {

    JComboBox actionJComBox;

    ActionPanel(DrawingPanel drawingPanel, FigurePanel figures){
        this.setLayout(new GridBagLayout());

        TitledBorder actionPanelBorder = BorderFactory.createTitledBorder("Figure Actions");
        actionPanelBorder.setTitleFont(arialBold12);
        this.setBorder(actionPanelBorder);

        actionJComBox = new JComboBox(figureActions);
        actionJComBox.setBounds(50,50,90,5);
        actionJComBox.setBackground(Color.white);

        ActionListener actionJComBoxActionListener = e -> {
            String figureJComBoxData = (String) figures.getSelectedItem();
            String actionJComBoxData = (String) actionJComBox.getSelectedItem();
            if (figureJComBoxData.equals("Line") && ( actionJComBoxData.equals("Enlarge") || actionJComBoxData.equals("Shrink") )) {
                actionJComBox.setSelectedIndex(0);
            } else {
                drawingPanel.setAction(actionJComBoxData);
            }
        };

        actionJComBox.addActionListener(actionJComBoxActionListener);

        this.add(actionJComBox, new GridBagConstraints(0, 0, 4, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 40));
    }

    public Object getSelectedItem() {
        return this.actionJComBox.getSelectedItem();
    }

    public void setSelectedIndex(int i) {
        this.actionJComBox.setSelectedIndex(i);
    }

    public void lockActionPanel(){
        this.actionJComBox.setEnabled(false);
    }
    
    public void unlockActionPanel() {
        this.actionJComBox.setEnabled(true);
    }
}
