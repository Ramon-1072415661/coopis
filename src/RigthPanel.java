
import Timer.PlacardPainel;
import javax.swing.*;
public class RigthPanel extends JPanel {
    public RigthPanel(NextPanel Npanel, PlacardPainel placardPainel) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        Npanel.setAlignmentX(LEFT_ALIGNMENT);
        placardPainel.setAlignmentX(LEFT_ALIGNMENT);
        add(Npanel);
        add(Box.createVerticalStrut(180));
        add(placardPainel);
        repaint();
    }
}
