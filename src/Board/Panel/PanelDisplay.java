package Board.Panel;

import Board.Player;

import javax.swing.*;
import java.awt.*;

public class PanelDisplay extends JPanel {
    public PanelDisplay(JPanel ...panels) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        for (JPanel panel : panels){
            panel.setAlignmentX(LEFT_ALIGNMENT);
            add(panel);

        }

    }
}
