package panel;

import javax.swing.*;

public class LeftPanel extends JPanel {
    public LeftPanel(HolderPanel hold, TimerPanel timer) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        hold.setAlignmentX(RIGHT_ALIGNMENT);
        timer.setAlignmentX(RIGHT_ALIGNMENT);
        add(hold);
        add(Box.createVerticalStrut(550));
        add(timer);
        repaint();
    }
}
