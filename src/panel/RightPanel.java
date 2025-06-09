package panel;

import javax.swing.*;

public class RightPanel extends JPanel {
    public RightPanel(NextPanel nextPanel, ScoresPanel scoresPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        nextPanel.setAlignmentX(LEFT_ALIGNMENT);
        scoresPanel.setAlignmentX(LEFT_ALIGNMENT);
        add(nextPanel);
        add(scoresPanel);
        repaint();
    }
}
