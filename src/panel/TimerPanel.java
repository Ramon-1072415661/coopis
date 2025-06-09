package panel;

import interfaces.ActualTimeObserver;
import managers.TimerManager;
import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel implements ActualTimeObserver {
    private static final String DEFAULT_TIME = "00:00:00";
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 25);
    private static final Font TIME_FONT = new Font("Arial", Font.BOLD, 32);

    private final JLabel timerLabel;

    public TimerPanel() {
        TimerManager.getInstance().addTimeMatchObserver(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel titleLabel = createLabel("Time", TITLE_FONT);
        timerLabel = createLabel(DEFAULT_TIME, TIME_FONT);

        add(titleLabel);
        add(timerLabel);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return label;
    }

    @Override
    public void updateOnTimeChange(String actualTime) {
        timerLabel.setText(actualTime);
    }

    @Override
    public void reset() {
        timerLabel.setText(DEFAULT_TIME);
    }
}