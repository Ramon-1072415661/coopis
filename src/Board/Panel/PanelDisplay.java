package Board.Panel;

import Board.Player;

import javax.swing.*;
import java.awt.*;

public class PanelDisplay extends JPanel {
    public PanelDisplay(JPanel panel) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        add(panel);

    }
}
