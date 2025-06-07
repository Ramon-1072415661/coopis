import Timer.PlacardPanel;

import javax.swing.*;

public class RightPanel extends JPanel {
    public RightPanel(NextPanel Npanel, PlacardPanel placardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        Npanel.setAlignmentX(LEFT_ALIGNMENT);
        placardPanel.setAlignmentX(LEFT_ALIGNMENT);
        add(Npanel);
        add(Box.createVerticalStrut(180));
        add(placardPanel);
        repaint();
    }
}
