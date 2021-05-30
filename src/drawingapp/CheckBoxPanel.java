package drawingapp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * CheckBox Panel lets a user select a color for the figures to be drawn in.
 * @author yourName
 */
public class CheckBoxPanel extends JPanel implements Constants {

    String selectedColor = "Red";
    
    CheckBoxPanel(DrawingPanel drawingPanel){
        this.setLayout(new GridBagLayout());

        TitledBorder checkBoxBorder = BorderFactory.createTitledBorder("Colors");
        checkBoxBorder.setTitleFont(arialBold12);
        this.setBorder(checkBoxBorder);

        JCheckBox checkBoxRed = new JCheckBox("");
        checkBoxRed.setBackground(Color.red);
        checkBoxRed.setForeground(Color.white);
        checkBoxRed.setSelected(true);

        JCheckBox checkBoxBlack = new JCheckBox("");
        checkBoxBlack.setBackground(Color.black);
        checkBoxBlack.setForeground(Color.white);

        JCheckBox checkBoxGreen = new JCheckBox("");
        checkBoxGreen.setBackground(Color.green);
        checkBoxGreen.setForeground(Color.white);

        JCheckBox checkBoxBlue = new JCheckBox("");
        checkBoxBlue.setBackground(Color.blue);
        checkBoxBlue.setForeground(Color.white);

        ActionListener redBoxActionListener = e -> {
            if (selectedColor.equals("Red")){
                checkBoxRed.setSelected(true);
            } else {
                checkBoxBlack.setSelected(false);
                checkBoxBlue.setSelected(false);
                checkBoxGreen.setSelected(false);
                selectedColor = "Red";
                drawingPanel.setSelectedColor(Color.red);
            }
        };

        ActionListener greenBoxActionListener = e -> {
            if (selectedColor.equals("Green")){
                checkBoxGreen.setSelected(true);
            } else {
                checkBoxBlack.setSelected(false);
                checkBoxBlue.setSelected(false);
                checkBoxRed.setSelected(false);
                selectedColor = "Green";
                drawingPanel.setSelectedColor(Color.green);
            }
        };

        ActionListener blueBoxActionListener = e -> {
            if (selectedColor.equals("Blue")){
                checkBoxBlue.setSelected(true);
            } else {
                checkBoxBlack.setSelected(false);
                checkBoxRed.setSelected(false);
                checkBoxGreen.setSelected(false);
                selectedColor = "Blue";
                drawingPanel.setSelectedColor(Color.blue);
            }
        };

        ActionListener blackBoxActionListener = e -> {
            if (selectedColor.equals("Black")){
                checkBoxBlack.setSelected(true);
            } else {
                checkBoxRed.setSelected(false);
                checkBoxBlue.setSelected(false);
                checkBoxGreen.setSelected(false);
                selectedColor = "Black";
                drawingPanel.setSelectedColor(Color.black);
            }
        };

        checkBoxRed.addActionListener(redBoxActionListener);
        checkBoxGreen.addActionListener(greenBoxActionListener);
        checkBoxBlue.addActionListener(blueBoxActionListener);
        checkBoxBlack.addActionListener(blackBoxActionListener);

        this.add(checkBoxRed, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 20));
        this.add(checkBoxGreen, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 20));
        this.add(checkBoxBlue, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 20));
        this.add(checkBoxBlack, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 20));
    }

    public String getSelectedColor() {
        return selectedColor;
    }
}
