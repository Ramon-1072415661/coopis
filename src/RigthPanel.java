
import Timer.PanelTimer;
import javax.swing.*;
public class RigthPanel extends JPanel {
    public RigthPanel(NextPanel Npanel, PanelTimer timerPanel) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        Npanel.setAlignmentX(LEFT_ALIGNMENT);
        timerPanel.setAlignmentX(LEFT_ALIGNMENT);
        add(Npanel);
        add(Box.createVerticalStrut(180));
        add(timerPanel);
        repaint();
    }
}
