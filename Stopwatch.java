import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch extends JFrame implements ActionListener {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;
    private boolean running = false;

    public Stopwatch() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timeLabel = new JLabel(formatTime(elapsedTime));
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        add(timeLabel);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        add(startButton);
        add(stopButton);
        add(resetButton);

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        timer = new Timer(1000, e -> {
            elapsedTime += 1000;
            timeLabel.setText(formatTime(elapsedTime));
        });

        stopButton.setEnabled(false);

        setVisible(true);
    }

    private String formatTime(int millis) {
        int seconds = (millis / 1000) % 60;
        int minutes = (millis / 60000) % 60;
        int hours = (millis / 3600000);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton && !running) {
            timer.start();
            running = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        } else if (e.getSource() == stopButton) {
            timer.stop();
            running = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        } else if (e.getSource() == resetButton) {
            timer.stop();
            elapsedTime = 0;
            timeLabel.setText(formatTime(elapsedTime));
            running = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Stopwatch::new);
    }
}