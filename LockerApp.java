import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp {
    private JFrame frame;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private String savedPassword = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LockerApp window = new LockerApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LockerApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Lock Class");
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new NumberButtonListener());
            buttonPanel.add(button);
        }

        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        statusLabel = new JLabel("Enter Password");
        controlPanel.add(statusLabel, BorderLayout.NORTH);

        passwordField = new JPasswordField();
        controlPanel.add(passwordField, BorderLayout.CENTER);

        JPanel buttonControlPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> passwordField.setText(""));
        buttonControlPanel.add(clearButton);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        buttonControlPanel.add(enterButton);

        controlPanel.add(buttonControlPanel, BorderLayout.SOUTH);



        frame.getContentPane().add(controlPanel, BorderLayout.EAST);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            passwordField.setText(passwordField.getText() + source.getText());
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passwordField.getPassword());

            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else if (enteredPassword.equals(savedPassword)) {
                statusLabel.setText("Correct Password");
            } else {
                statusLabel.setText("Incorrect Password");
            }

            passwordField.setText("");
        }
    }
}