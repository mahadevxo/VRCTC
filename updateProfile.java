package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class updateProfile implements ActionListener
{
    JFrame jeff;
    JTextField newPassword, oldPassword;
    JButton submit, back;
    JLabel label = new JLabel("Old password incorrect"), newUsername;
    public void updater()
    {
        jeff = new JFrame("Edit Profile");
        newUsername = new JLabel(loginWindow.username.getText());
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        JLabel userna = new JLabel("Username: ");
        JLabel passOld = new JLabel("Old Password: ");
        JLabel passNew = new JLabel("New Password: ");
        submit = new JButton("Submit");
        back = new JButton("Back");
        JLabel templabel = new JLabel("");

        userna.setBounds(10, 20, 200, 20 );
        newUsername.setBounds(110, 20, 100, 20);
        passOld.setBounds(10, 50, 200, 20);
        oldPassword.setBounds(110, 50, 100, 20);
        passNew.setBounds(10, 80, 200, 20);
        newPassword.setBounds(110, 80, 100, 20);
        label.setBounds(70, 110, 200, 20);
        submit.setBounds(90, 125, 50, 20);
        back.setBounds(150, 125, 50, 20);

        jeff.add(newUsername);
        jeff.add(oldPassword);
        jeff.add(newPassword);
        jeff.add(userna);
        jeff.add(passNew);
        jeff.add(passOld);
        jeff.add(label);
        jeff.add(submit);
        jeff.add(back);
        jeff.add(templabel);

        label.setVisible(false);

        submit.addActionListener(this);
        back.addActionListener(this);

        jeff.pack();
        jeff.setSize(300, 200);
        jeff.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submit)
        {
            System.out.println("pressed lol");
            try
            {
                if(oldPasswordCheck(oldPassword.getText()))
                {
                    System.out.println("old correct lol");
                    changeData(newUsername.getText(), newPassword.getText());
                }
            }
            catch(Exception exception)
            {
                System.out.println("Error: " + exception);
            }
        }

        else if(e.getSource() == back)
        {
            System.out.println("i pressed this?");
            jeff.dispose();
            jeff = null;
            try
            {
                (new booker()).booker(loginWindow.username.getText());
            }
            catch (IOException ex)
            {
                System.out.println("Error: " + ex);
            }
        }
    }
    public boolean oldPasswordCheck(String pass) throws IOException
    {
        boolean b = false;
        BufferedReader brrrrr = new BufferedReader(new FileReader("loginDatabase.csv"));
        String s = "";
        while((s = brrrrr.readLine()) != null)
        {
            String data[] = s.split(",");
            if(data[0].equals(loginWindow.username.getText()) && data[1].equals(pass))
            {
                return true;
            }
        }
        return false;
    }
    public void changeData(String username, String password) throws IOException
    {
        BufferedReader brrrrrrrrrrrrrrrrrrrr = new BufferedReader(new FileReader("loginDatabase.csv"));
        String s = null;
        String oldData = "";
        while((s = brrrrrrrrrrrrrrrrrrrr.readLine()) != null)
        {
            String[] data = s.split(",");
            if(data[0].equals(username))
            {
                if(oldData.isBlank())
                {
                    oldData = username + "," + password;
                }
                else
                {
                    oldData += ("\n" + username + "," + password);
                }
            }
            else
            {
                oldData += ("\n" + s);
            }
        }
        System.out.println(oldData);
        FileWriter writerbooboo = new FileWriter("loginDatabase.csv");
        writerbooboo.write(oldData);
        writerbooboo.flush();
        writerbooboo.close();

        new booker();
    }
}
