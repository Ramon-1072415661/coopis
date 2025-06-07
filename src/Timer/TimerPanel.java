package Timer;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel implements ActualTimeObserver {
    private final SingletonTimer timer = SingletonTimer.getInstance();
    private final JLabel timerLabel;
    public TimerPanel() {
        timer.addTimeMacthObserver(this);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
        Font tittleFont = new Font("Arial",Font.BOLD,25);
        Font timeFont = new Font("Arial", Font.BOLD,32);
        JLabel tittleLabel = new JLabel("Time");
        tittleLabel.setFont(tittleFont);
        tittleLabel.setForeground(Color.WHITE);
        tittleLabel.setAlignmentX(RIGHT_ALIGNMENT);
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(timeFont);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setAlignmentX(RIGHT_ALIGNMENT);
        add(tittleLabel);
        add(timerLabel);

    }
    @Override
    public void updateOnTimeChange(String actualTime) {
        timerLabel.setText(actualTime);
        repaint();
    }

    @Override
    public void reset() {
        timerLabel.setText("00:00:00");
        repaint();
    }
}
