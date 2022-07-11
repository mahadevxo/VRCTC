package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class loginWindow implements ActionListener {
    //public class variables
    JButton login, exit, register;
    public static JTextField username;
    JPasswordField password;
    public JFrame jeff;
    JLabel errorLabel = new JLabel("Unknown error has occoured");
    JLabel loginData = new JLabel();

    public loginWindow() throws IOException
    {
        jeff = new JFrame();
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jeff.setTitle("Login Window");
        ImageIcon image = new ImageIcon("vrctc.png");
        jeff.setIconImage(image.getImage());
        username = new JTextField();
        password = new JPasswordField();
        login = new JButton("Login");
        register = new JButton("Register");
        exit = new JButton("Exit");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password");

        usernameLabel.setBounds(10, 50, 80, 20);
        passwordLabel.setBounds(10, 100, 80, 20);
        username.setBounds(100, 50, 120, 20);
        password.setBounds(100, 100, 120, 20);
        register.setBounds(140, 150, 70, 20);
        login.setBounds(50, 150, 50, 20);
        exit.setBounds(100, 190, 50, 20);
        errorLabel.setBounds(10, 250, 40, 20);

        login.addActionListener(this);
        register.addActionListener(this);
        exit.addActionListener(this);

        jeff.add(errorLabel);
        jeff.add(usernameLabel);
        jeff.add(passwordLabel);
        jeff.add(username);
        jeff.add(password);
        jeff.add(login);
        jeff.add(register);
        jeff.add(exit);

        jeff.setSize(270, 270);

        jeff.setLayout(null);
        jeff.setVisible(true);
        jeff.setResizable(false);
        jeff.setAlwaysOnTop(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login) {
            System.out.println("Login button clicked");
            System.out.println("Username:\t " + username.getText() + "\nPassword:\t " + password.getText());
            jeff.add(loginData);
            loginData.setBounds(70, 170, 200, 20);
            try
            {
                if (databaseChecker.check(username.getText(), password.getText()) == true) {
                    System.out.println("Login Successful");
                    jeff.dispose();
                    (new booker()).booker(username.getText());
                }
                else if (!databaseChecker.check(username.getText(), password.getText()))
                {
                    System.out.println("Wrong Password");
                    loginData.setText("Wrong Password");
                }
                else
                {
                    System.out.println("Error");
                }
            }
            catch (IOException exception)
            {
                System.out.println("Error in loginWindow :: " + exception);
            }
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == register) {
            try
            {
                jeff.dispose();
                jeff = null;

                registerWindow rew = new registerWindow();
            }
            catch (Exception error)
            {
                System.out.println("Error " + error);
            }
        }
    }
}