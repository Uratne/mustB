package drawingapp;

import javax.swing.*;
import java.awt.*;


public class ControlPanel extends JPanel implements Constants {

    ControlPanel(FigurePanel figurePanel){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(450, 50));

        this.add(figurePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 10));
    }
}
