package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ticketView implements ActionListener
{
    JFrame jeff = new JFrame("Your Tickets");
    JLabel[] labels;
    JRadioButton[] buttons;
    int ticketCount;
    String[] tickets;
    JButton cancel = new JButton("Cancel"), back = new JButton("Back");
    public int getNumberOfTickets() throws IOException
    {
        String username = loginWindow.username.getText();
        BufferedReader brrrrr = new BufferedReader(new FileReader("bookingDatabase.csv"));
        String s = null;
        int counter = 0;
        while((s = brrrrr.readLine()) != null)
        {
            if(s.split(",")[0].equals(username))
            {
                counter++;
            }
        }
        return counter;
    }

    public void getTickets() throws IOException
    {
        String username = loginWindow.username.getText();
        BufferedReader newBrrr = new BufferedReader(new FileReader("bookingDatabase.csv"));
        String s = null;
        int i = 0;
        while((s = newBrrr.readLine()) != null)
        {
            if(i < ticketCount)
            {
                if(s.split(",")[0].equals(username))
                {
                    tickets[i] = s;
                    System.out.println(tickets[i]);
                    i++;
                }
            }
        }
    }

    public void view() throws IOException
    {
        jeff = new JFrame("Your Tickets");
        ticketCount = getNumberOfTickets();
        System.out.println("number of tickets: " + ticketCount);

        labels = new JLabel[ticketCount];
        buttons = new JRadioButton[ticketCount];

        int x = 700;
        int y = (30*ticketCount) + 100;

        tickets = new String[ticketCount];
        getTickets();
        for(int i = 0; i < ticketCount; i++)
        {
            labels[i] = new JLabel("");
            System.out.println("----> " + tickets[i]);
            String[] data = tickets[i].split(",");
            String ticket = "From: " + data[1] + " To: " + data[2] + " On: " + data[3] + " - " + data[4] + "  On: " + data[5] + " Seat: " + data[6];
            buttons[i] = new JRadioButton();
            buttons[i].setBounds(30, i*30, 500, 20);
            labels[i].setText(ticket);
            labels[i].setBounds(60, i*30, 800, 20);
            jeff.add(buttons[i]);
            jeff.add(labels[i]);
        }

        cancel.setBounds(40, ticketCount*30 + 10, 50, 20);
        cancel.addActionListener(this);
        jeff.add(cancel);
        back.setBounds(100, ticketCount*30 + 10, 50, 20);
        back.addActionListener(this);
        jeff.add(back);

        JLabel temp = new JLabel("");
        temp.setBounds(0,0,0,0);
        jeff.add(temp);
        jeff.pack();
        jeff.setSize(x, y);
        jeff.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == back)
        {
            jeff.dispose();
            jeff = null;
            try
            {
                new booker().booker(loginWindow.username.getText());
            }
            catch (IOException ex)
            {
                System.out.println("Error " + ex);
            }
        }
        else if(e.getSource() == cancel)
        {
            int nocancelTickets = 0;
            for(int i = 0; i < ticketCount; i++)
            {
                if(buttons[i].isSelected())
                {
                    nocancelTickets+=1;
                }
            }
            int[] cancelTickets = new int[nocancelTickets];
            int k = 0;
            for(int i = 0; i < ticketCount; i++)
            {
                if(buttons[i].isSelected())
                {
                    cancelTickets[k] = i;
                    k+=1;
                }
            }
            for(int i = 0; i < cancelTickets.length; i++)
            {
                String dada = labels[i].getText();
                String newDaddy[] = dada.split(" ");
                String text = loginWindow.username.getText() + "," + newDaddy[1] + "," + newDaddy[3] + "," + newDaddy[5] + " " + newDaddy[6] + "," + newDaddy[8] + "," + newDaddy[11] + "," + newDaddy[13];
                System.out.println("New dadday --> " + text);
                try
                {
                    removeTicket(text);

                    if(waitingList.doesWaitingListExist(text.split(",")[4], text.split(",")[5]))
                    {
                        String popped = waitingList.pop(text.split(",")[4], text.split(",")[5]);
                        System.out.println("Popped --> " + popped);
                        waitingList.addTicket(popped, text.split(",")[6]);
                    }
                    else
                    {
                        System.out.println("cancelled ticket");
                        trainData.removeAndAdd(text.split(",")[4], text.split(",")[5]);
                    }
                    jeff.dispose();
                    new ticketView().view();
                }
                catch(IOException ex)
                {
                    System.out.println("Error: " + ex);
                }
            }
        }
    }
    public void removeTicket(String remove) throws IOException
    {
        BufferedReader brrrrrrrrrrrr = new BufferedReader(new FileReader("bookingDatabase.csv"));
        String s = null, write = "";
        while((s = brrrrrrrrrrrr.readLine()) != null)
        {
            if(!s.equalsIgnoreCase(remove))
            {
                if(write.isEmpty())
                {
                    write += s;
                }
                else
                {
                    write += "\n"+ s;
                }
            }
        }
        FileWriter writer = new FileWriter("bookingDatabase.csv");
        writer.write(write);
        writer.close();
    }
}