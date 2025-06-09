package panel;

import interfaces.TimeScoreObservers;
import managers.DisplayManager;
import managers.TimerManager;
import timer.TimeRegister;
import utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class ScoresPanel extends JPanel implements TimeScoreObservers {
    private static final int CORNER_RADIUS = 45;
    private final JPanel columnStartTime;
    private final JPanel columnFinalTime;
    private final JPanel columnScore;
    private final DisplayManager displayManager = DisplayManager.getInstance();
    private final int cornerRadius = displayManager.getCornerRadius();

    public ScoresPanel() {
        TimerManager timer = TimerManager.getInstance();
        timer.addTimeObservers(this);

        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        Font font = new Font("Arial", Font.BOLD, 24);
        columnStartTime = new JPanel();
        columnStartTime.setOpaque(false);
        columnStartTime.setLayout(new BoxLayout(columnStartTime, BoxLayout.Y_AXIS));
        JLabel startLabel = new JLabel("Start Time");
        startLabel.setForeground(Color.WHITE);
        startLabel.setFont(font);
        startLabel.setAlignmentX(CENTER_ALIGNMENT);
        startLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        columnStartTime.add(startLabel);

        columnFinalTime = new JPanel();
        columnFinalTime.setOpaque(false);
        columnFinalTime.setLayout(new BoxLayout(columnFinalTime, BoxLayout.Y_AXIS));
        JLabel finalLabel = new JLabel("Final Time");
        finalLabel.setForeground(Color.WHITE);
        finalLabel.setFont(font);
        finalLabel.setAlignmentX(CENTER_ALIGNMENT);
        finalLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        columnFinalTime.add(finalLabel);

        columnScore = new JPanel();
        columnScore.setOpaque(false);
        columnScore.setLayout(new BoxLayout(columnScore, BoxLayout.Y_AXIS));
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(font);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        columnScore.add(scoreLabel);

        add(columnStartTime);
        add(columnFinalTime);
        add(columnScore);

        setMaximumSize(getPreferredSize());
    }


    private void addRegistry(String startTime, String finalTime, int score) {
        Font font = new Font("Arial", Font.BOLD, 18);
        JLabel startTimeLabel = new JLabel(startTime);
        JLabel finalTimeLabel = new JLabel(finalTime);
        JLabel scoreLabel = new JLabel(String.valueOf(score));
        startTimeLabel.setAlignmentX(CENTER_ALIGNMENT);
        finalTimeLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        startTimeLabel.setForeground(Color.WHITE);
        finalTimeLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        startTimeLabel.setFont(font);
        finalTimeLabel.setFont(font);
        scoreLabel.setFont(font);
        columnStartTime.add(startTimeLabel);
        columnStartTime.add(Box.createVerticalStrut(10));
        columnFinalTime.add(finalTimeLabel);
        columnFinalTime.add(Box.createVerticalStrut(10));
        columnScore.add(scoreLabel);
        columnScore.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Path2D path = getPath2D();

        g2.setColor(Colors.PANELS_BACKGROUND);
        g2.fill(path);
        g2.dispose();

        super.paintComponent(g);
    }

    private Path2D getPath2D() {
        int w = getWidth();
        int h = getHeight();
        int r = cornerRadius;

        // Build a custom shape with rounded top-left and bottom-left corners
        Path2D path = new Path2D.Float();
        path.moveTo(0, 0);
        path.lineTo(w - r, 0);
        path.quadTo(w, 0, w, r);
        path.lineTo(w, h - r);
        path.quadTo(w, h, w - r, h);
        path.lineTo(0, h);
        path.closePath();
        return path;
    }

    @Override
    public void timeUpdate(TimeRegister register, int score) {
        addRegistry(register.getStartTime(), register.getEndTime(), score);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

}
