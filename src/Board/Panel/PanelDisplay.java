package Board.Panel;

import Board.Player;

import javax.swing.*;
import java.awt.*;

public class PanelDisplay extends JPanel {
    public PanelDisplay(Player player) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        HolderPanel holder = new HolderPanel(player);
        Dimension holder_size = holder.getPreferredSize();
        holder.setMaximumSize(holder_size);
        holder.setMaximumSize(holder_size);
        add(holder);
    }
}
