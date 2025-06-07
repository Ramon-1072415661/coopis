package Timer;
import javax.swing.*;
import java.awt.*;

public class PlacardPainel extends JPanel implements TimeScoreObservers {
    private JPanel columnStartTime;
    private JPanel columnFinalTime;
    private JPanel columnScore;
    private SingletonTimer timer = SingletonTimer.getInstance();

    public PlacardPainel() {
        timer.addTimeObservers(this);
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
        Font font = new Font("Arial", Font.BOLD, 24);
        columnStartTime = new JPanel();
        columnStartTime.setOpaque(false);
        columnStartTime.setLayout(new BoxLayout(columnStartTime,BoxLayout.Y_AXIS));
        JLabel startLabel = new JLabel("Start Time");
        startLabel.setForeground(Color.WHITE);
        startLabel.setFont(font);
        startLabel.setAlignmentX(CENTER_ALIGNMENT);
        startLabel.setBorder(BorderFactory.createEmptyBorder(5,0,15,0));
        columnStartTime.add(startLabel);

        columnFinalTime = new JPanel();
        columnFinalTime.setOpaque(false);
        columnFinalTime.setLayout(new BoxLayout(columnFinalTime,BoxLayout.Y_AXIS));
        JLabel finalLabel = new JLabel("Final Time");
        finalLabel.setForeground(Color.WHITE);
        finalLabel.setFont(font);
        finalLabel.setAlignmentX(CENTER_ALIGNMENT);
        finalLabel.setBorder(BorderFactory.createEmptyBorder(5,0,15,0));
        columnFinalTime.add(finalLabel);

        columnScore = new JPanel();
        columnScore.setOpaque(false);
        columnScore.setLayout(new BoxLayout(columnScore,BoxLayout.Y_AXIS));
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(font);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        columnScore.add(scoreLabel);

        add(columnStartTime);
        add(columnFinalTime);
        add(columnScore);

        setMaximumSize(getPreferredSize());
    }


    private void addRegistry(String startTime,String finalTime, int score){
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
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0x21222C));
        g2.fillRoundRect(0,0,getWidth(),getHeight(),45,45);
        g2.dispose();
    }

    @Override
    public void timeUpdate(TimeRegister register, int score) {
        addRegistry(register.startTime(),register.finalTime(),score);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,300);
    }

}
