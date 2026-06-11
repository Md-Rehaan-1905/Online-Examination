import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultPanel extends JPanel {

    OnlineExam app;
    JLabel titleLabel, scoreLabel, gradeLabel, messageLabel;
    JButton logoutButton;

    public ResultPanel(OnlineExam app) {
        this.app = app;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

        titleLabel = new JLabel("Exam Complete!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(100, 180, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Your Score: 0 / 5");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gradeLabel = new JLabel("Grade: -");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gradeLabel.setForeground(new Color(100, 220, 140));
        gradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(150, 150, 180));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutButton = new JButton("LOGOUT");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(160, 50, 50));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setMaximumSize(new Dimension(200, 45));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showScreen("login");
            }
        });

        add(titleLabel);
        add(Box.createVerticalStrut(30));
        add(scoreLabel);
        add(Box.createVerticalStrut(15));
        add(gradeLabel);
        add(Box.createVerticalStrut(10));
        add(messageLabel);
        add(Box.createVerticalStrut(40));
        add(logoutButton);
    }

    void showResult() {
        int s = app.score;
        int total = 5;
        scoreLabel.setText("Your Score: " + s + " / " + total);

        String grade, message;

        if      (s == 5) { grade = "A+"; message = "Perfect score! Outstanding!"; }
        else if (s == 4) { grade = "A";  message = "Excellent work!"; }
        else if (s == 3) { grade = "B";  message = "Good job!"; }
        else if (s == 2) { grade = "C";  message = "Keep practicing!"; }
        else             { grade = "F";  message = "Better luck next time."; }

        gradeLabel.setText("Grade: " + grade);
        messageLabel.setText(message);
    }
}