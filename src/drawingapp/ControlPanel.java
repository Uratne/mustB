package drawingapp;

import javax.swing.*;
import java.awt.*;

/**
 * Control panel hold the following panels Figure Panel, Action Panel, Figure Data Panel, Checkbox Panel.
 * Layout for the control panel is set to gridBag layout.
 * @see FigurePanel
 * @see ActionPanel
 * @see FigureDataPanel
 * @see CheckBoxPanel
 * @author yourName
 */
public class ControlPanel extends JPanel implements Constants {

    ControlPanel(FigurePanel figurePanel, ActionPanel actionPanel, FigureDataPanel figureDataPanel, CheckBoxPanel checkBoxPanel){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(450, 100));

        this.add(figurePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 10));
        this.add(actionPanel, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 10));
        this.add(figureDataPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 10));
        this.add(checkBoxPanel, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 10));
    }
}
