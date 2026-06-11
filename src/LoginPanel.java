import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {

    OnlineExam app;

    JLabel titleLabel, userLabel, passLabel, feedbackLabel;
    JLabel newUserLabel, newPassLabel;
    JTextField userField, newUserField;
    JPasswordField passField, newPassField;
    JButton loginButton, updateButton;
    JPanel updatePanel;

    public LoginPanel(OnlineExam app) {
        this.app = app;

        setBackground(new Color(18, 28, 48));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        titleLabel = new JLabel("📝 Online Examination");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 180, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 13));
        userLabel.setForeground(new Color(200, 200, 220));
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        userField = new JTextField(15);
        styleField(userField);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passLabel.setForeground(new Color(200, 200, 220));
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passField = new JPasswordField(15);
        styleField(passField);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        feedbackLabel.setForeground(new Color(255, 100, 100));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton = createButton("LOGIN", new Color(100, 180, 255), new Color(18, 28, 48));
        updateButton = createButton("UPDATE PROFILE / PASSWORD", new Color(70, 100, 160), Color.WHITE);

        // update profile panel (hidden by default)
        updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        updatePanel.setBackground(new Color(18, 28, 48));
        updatePanel.setVisible(false);

        newUserLabel = new JLabel("New Username");
        newUserLabel.setFont(new Font("Arial", Font.BOLD, 13));
        newUserLabel.setForeground(new Color(200, 200, 220));
        newUserLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        newUserField = new JTextField(15);
        styleField(newUserField);

        newPassLabel = new JLabel("New Password");
        newPassLabel.setFont(new Font("Arial", Font.BOLD, 13));
        newPassLabel.setForeground(new Color(200, 200, 220));
        newPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        newPassField = new JPasswordField(15);
        styleField(newPassField);

        JButton saveButton = createButton("SAVE CHANGES", new Color(50, 160, 100), Color.WHITE);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newUser = newUserField.getText().trim();
                String newPass = new String(newPassField.getPassword()).trim();
                if (!newUser.isEmpty()) app.username = newUser;
                if (!newPass.isEmpty()) app.password = newPass;
                feedbackLabel.setForeground(new Color(100, 220, 140));
                feedbackLabel.setText("Profile updated successfully!");
                updatePanel.setVisible(false);
                newUserField.setText("");
                newPassField.setText("");
            }
        });

        updatePanel.add(Box.createVerticalStrut(15));
        updatePanel.add(newUserLabel);
        updatePanel.add(Box.createVerticalStrut(6));
        updatePanel.add(newUserField);
        updatePanel.add(Box.createVerticalStrut(15));
        updatePanel.add(newPassLabel);
        updatePanel.add(Box.createVerticalStrut(6));
        updatePanel.add(newPassField);
        updatePanel.add(Box.createVerticalStrut(10));
        updatePanel.add(saveButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUser = userField.getText().trim();
                String enteredPass = new String(passField.getPassword());
                if (enteredUser.equals(app.username) && enteredPass.equals(app.password)) {
                    userField.setText("");
                    passField.setText("");
                    feedbackLabel.setText(" ");
                    app.examPanel.startExam();
                    app.showScreen("exam");
                } else {
                    feedbackLabel.setForeground(new Color(255, 100, 100));
                    feedbackLabel.setText("Invalid username or password!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePanel.setVisible(!updatePanel.isVisible());
            }
        });

        add(titleLabel);
        add(Box.createVerticalStrut(30));
        add(userLabel);
        add(Box.createVerticalStrut(6));
        add(userField);
        add(Box.createVerticalStrut(15));
        add(passLabel);
        add(Box.createVerticalStrut(6));
        add(passField);
        add(Box.createVerticalStrut(10));
        add(feedbackLabel);
        add(Box.createVerticalStrut(10));
        add(loginButton);
        add(Box.createVerticalStrut(10));
        add(updateButton);
        add(updatePanel);
    }

    void styleField(JTextField field) {
        field.setMaximumSize(new Dimension(300, 40));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBackground(new Color(30, 40, 65));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setMaximumSize(new Dimension(300, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }
}