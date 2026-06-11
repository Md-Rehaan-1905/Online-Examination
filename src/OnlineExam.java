import javax.swing.*;
import java.awt.*;

public class OnlineExam extends JFrame {

    CardLayout cardLayout;
    JPanel mainContainer;

    // user data
    String username = "rehaan";
    String password = "1234";
    int score = 0;

    // panels
    LoginPanel loginPanel;
    ExamPanel examPanel;
    ResultPanel resultPanel;

    public OnlineExam() {
        setTitle("Online Examination System");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        loginPanel   = new LoginPanel(this);
        examPanel    = new ExamPanel(this);
        resultPanel  = new ResultPanel(this);

        mainContainer.add(loginPanel,  "login");
        mainContainer.add(examPanel,   "exam");
        mainContainer.add(resultPanel, "result");

        add(mainContainer);
        cardLayout.show(mainContainer, "login");
        setVisible(true);
    }

    void showScreen(String screen) {
        cardLayout.show(mainContainer, screen);
    }

    public static void main(String[] args) {
        new OnlineExam();
    }
}