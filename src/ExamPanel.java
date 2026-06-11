import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExamPanel extends JPanel {

    OnlineExam app;

    // questions, options, answers
    String[] questions = {
            "What is the capital of India?",
            "Which planet is closest to the Sun?",
            "What is 12 x 12?",
            "Who invented the telephone?",
            "What is the chemical symbol for water?"
    };

    String[][] options = {
            {"Mumbai", "Delhi", "Chennai", "Kolkata"},
            {"Venus", "Earth", "Mercury", "Mars"},
            {"124", "144", "164", "104"},
            {"Edison", "Tesla", "Bell", "Marconi"},
            {"H2O", "CO2", "O2", "H2"}
    };

    int[] answers = {1, 2, 1, 2, 0}; // index of correct option

    int currentQuestion = 0;
    int score = 0;
    int timeLeft = 30; // seconds per question
    Timer timer;

    JLabel timerLabel, questionLabel, questionNumLabel;
    JRadioButton[] optionButtons;
    ButtonGroup buttonGroup;
    JButton nextButton;
    JPanel optionsPanel;

    public ExamPanel(OnlineExam app) {
        this.app = app;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        timerLabel = new JLabel("Time Left: 30s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(new Color(255, 180, 50));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionNumLabel = new JLabel("Question 1 of 5");
        questionNumLabel.setFont(new Font("Arial", Font.BOLD, 13));
        questionNumLabel.setForeground(new Color(150, 150, 180));
        questionNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionLabel = new JLabel("Question text here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(new Color(18, 28, 48));
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton("Option");
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionButtons[i].setForeground(new Color(200, 200, 220));
            optionButtons[i].setBackground(new Color(18, 28, 48));
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
            optionsPanel.add(Box.createVerticalStrut(8));
        }

        nextButton = new JButton("NEXT →");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(100, 180, 255));
        nextButton.setForeground(new Color(18, 28, 48));
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.setMaximumSize(new Dimension(300, 45));
        nextButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleNext();
            }
        });

        add(timerLabel);
        add(Box.createVerticalStrut(10));
        add(questionNumLabel);
        add(Box.createVerticalStrut(20));
        add(questionLabel);
        add(Box.createVerticalStrut(20));
        add(optionsPanel);
        add(Box.createVerticalStrut(20));
        add(nextButton);
    }

    void startExam() {
        currentQuestion = 0;
        score = 0;
        loadQuestion();
    }

    void loadQuestion() {
        if (timer != null) timer.stop();

        timeLeft = 30;
        timerLabel.setText("Time Left: 30s");
        timerLabel.setForeground(new Color(255, 180, 50));
        buttonGroup.clearSelection();

        questionNumLabel.setText("Question " + (currentQuestion + 1) + " of " + questions.length);
        questionLabel.setText("<html><body style='width:350px'>" + questions[currentQuestion] + "</body></html>");

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestion][i]);
        }

        nextButton.setText(currentQuestion == questions.length - 1 ? "SUBMIT" : "NEXT →");

        // start countdown timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");
                if (timeLeft <= 10) {
                    timerLabel.setForeground(new Color(255, 80, 80));
                }
                if (timeLeft == 0) {
                    handleNext(); // auto submit when time runs out
                }
            }
        });
        timer.start();
    }

    void handleNext() {
        timer.stop();

        // check selected answer
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected() && i == answers[currentQuestion]) {
                score++;
                break;
            }
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            app.score = score;
            app.resultPanel.showResult();
            app.showScreen("result");
        }
    }
}