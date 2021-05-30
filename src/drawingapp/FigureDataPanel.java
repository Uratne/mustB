package drawingapp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Holds the figure that is selected in Figure Panel.
 * @see FigurePanel
 * @author yourName
 */
public class FigureDataPanel extends JPanel implements Constants {

    JLabel currentActionLabel;
    
    FigureDataPanel(){
        this.setLayout(new GridBagLayout());

        TitledBorder figureDataBorder = BorderFactory.createTitledBorder("Current Action");
        figureDataBorder.setTitleFont(arialBold12);
        this.setBorder(figureDataBorder);
        
        currentActionLabel = new JLabel("Circle");
        currentActionLabel.setFont(arialBold20);

        this.add(currentActionLabel, new GridBagConstraints(0, 0, 4, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 40));
    }

    public void setText(String jComBoxData) {
        this.currentActionLabel.setText(jComBoxData);
    }

    public String getCurrentActionLabel() {
        return currentActionLabel.getText();
    }
}
