package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class registerWindow implements ActionListener {
    JLabel usernameLabel, passwordLabel, usernameExistLabel;
    JTextField username;
    JPasswordField password;
    JButton submit, backToLogin;
    JFrame jeff = new JFrame();

    public registerWindow() throws IOException {
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jeff.setTitle("Registration Window");
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        username = new JTextField();
        password = new JPasswordField();
        submit = new JButton("Submit");
        backToLogin = new JButton("Back to login");

        usernameExistLabel = new JLabel("This username already exists");
        usernameExistLabel.setBounds(50, 120, 200, 20);

        backToLogin.setBounds(83, 140, 100, 20);

        usernameLabel.setBounds(10, 10, 100, 20);
        username.setBounds(150, 10, 100, 20);

        passwordLabel.setBounds(10, 50, 100, 20);
        password.setBounds(150, 50, 100, 20);

        submit.setBounds(100, 100, 70, 20);

        jeff.add(username);
        jeff.add(backToLogin);
        jeff.add(usernameLabel);
        jeff.add(password);
        jeff.add(passwordLabel);
        jeff.add(submit);
        jeff.add(usernameExistLabel);

        usernameExistLabel.setVisible(false);

        jeff.setSize(300, 200);

        jeff.setLayout(null);
        jeff.setVisible(true);
        jeff.setResizable(true);
        jeff.setAlwaysOnTop(true);

        submit.addActionListener(this);
        backToLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submit && !username.getText().isBlank() && !password.getText().isBlank())
        {
            try
            {
                FileWriter fow = new FileWriter("loginDatabase.csv", true);
                if (!doesUsernameExist(username.getText()))
                {
                    BufferedReader brrrrr = new BufferedReader(new FileReader("loginDatabase.csv"));
                    boolean b = brrrrr.readLine() == null;
                    if (b)
                    {
                        fow.write(username.getText() + "," + password.getText());
                    }
                    else
                    {
                        fow.write("\n" + username.getText() + "," + password.getText());
                    }
                    fow.flush();
                    fow.close();
                    jeff.dispose();
                    jeff = null;
                    loginWindow low = new loginWindow();
                }
                else
                {
                    usernameExistLabel.setVisible(true);
                }
            }
            catch (FileNotFoundException fnfe)
            {
                try
                {
                    new errorCatch().fileExceptionCatcher("loginDatabase.csv");
                    new registerWindow();
                    System.exit(0);
                }
                catch(Exception exception)
                {
                    try
                    {
                        new errorCatch().catcher(4);
                    }
                    catch (IOException ex)
                    {
                        System.out.println(ex);
                    }
                }
            }
            catch (IOException exception)
            {
                System.out.println("IOException");
                try {
                    new errorCatch().catcher(1);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
        if(e.getSource() == backToLogin)
        {
            jeff.dispose();
            jeff = null;
            try
            {
                new loginWindow();
            }
            catch (IOException ex)
            {
                System.out.println("Error");
            }
        }
    }

    public boolean doesUsernameExist(String name) throws IOException
    {
        BufferedReader brrr = null;
        try
        {
           brrr = new BufferedReader(new FileReader("loginDatabase.csv"));
        }
        catch(FileNotFoundException fnfe)
        {
            new errorCatch().fileExceptionCatcher("loginDatabase.csv");

        }
        String s = null;
        while ((s = brrr.readLine()) != null)
        {
            String data = s.split(",")[0];
            if (name.equals(data))
            {
                brrr.close();
                return true;
            }
        }
        brrr.close();
        return false;
    }
}