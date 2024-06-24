import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Registration {

    public static void main(String[] args) {
        // Show resolution selection dialog
        Dimension resolution = showResolutionDialog();
        if (resolution == null) {
            System.exit(0);
        }

        // Set the main frame size according to selected resolution
        JFrame frame = new JFrame();
        frame.setSize(resolution);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Step 1: Greeting window
        int response = JOptionPane.showConfirmDialog(null,
                "Добро пожаловать! Хотите зарегистрироваться в программе?",
                "Регистрация",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }

        // Step 2: Username input window
        String username = "";
        boolean validUsername = false;
        while (!validUsername) {
            username = JOptionPane.showInputDialog(null,
                    "Пожалуйста, введите логин (более 5 символов, без пробелов):",
                    "Регистрация",
                    JOptionPane.QUESTION_MESSAGE);

            if (username != null && username.length() > 5 && !username.contains(" ")) {
                validUsername = true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Логин должен быть больше 5 символов и не содержать пробелов.",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Step 3: Password input window
        JPasswordField passwordField = new JPasswordField();
        boolean validPassword = false;
        String password = "";
        while (!validPassword) {
            int passwordOption = JOptionPane.showConfirmDialog(null,
                    passwordField,
                    "Пожалуйста, введите пароль (более 8 символов, без пробелов, минимум одна цифра и одна буква):",
                    JOptionPane.OK_CANCEL_OPTION);

            if (passwordOption == JOptionPane.OK_OPTION) {
                password = new String(passwordField.getPassword());
                if (password.length() > 8 && password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*") && !password.contains(" ")) {
                    validPassword = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Пароль должен быть больше 8 символов, не содержать пробелов и содержать минимум одну цифру и одну букву.",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }

        // Step 4: Password confirmation window
        JPasswordField confirmPasswordField = new JPasswordField();
        boolean passwordsMatch = false;
        while (!passwordsMatch) {
            int confirmPasswordOption = JOptionPane.showConfirmDialog(null,
                    confirmPasswordField,
                    "Пожалуйста, повторите пароль:",
                    JOptionPane.OK_CANCEL_OPTION);

            if (confirmPasswordOption == JOptionPane.OK_OPTION) {
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if (confirmPassword.equals(password)) {
                    passwordsMatch = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Пароли не совпадают. Пожалуйста, попробуйте снова.",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }

        // Step 5: Successful registration window
        JOptionPane.showMessageDialog(null,
                "Регистрация успешна!",
                "Успех",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static Dimension showResolutionDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Выбор разрешения");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JRadioButton res1 = new JRadioButton("800x600");
        JRadioButton res2 = new JRadioButton("1024x768");
        JRadioButton res3 = new JRadioButton("1280x1024");
        group.add(res1);
        group.add(res2);
        group.add(res3);
        panel.add(res1);
        panel.add(res2);
        panel.add(res3);

        dialog.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (res1.isSelected()) {
            return new Dimension(800, 600);
        } else if (res2.isSelected()) {
            return new Dimension(1024, 768);
        } else if (res3.isSelected()) {
            return new Dimension(1280, 1024);
        } else {
            return null;
        }
    }
}
