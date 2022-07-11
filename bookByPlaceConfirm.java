package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class bookByPlaceConfirm implements ActionListener
{
    static String trainNumber;
    static String from, to;
    JFrame jeff;
    JLabel places, ticketsLeft, trainName, distance;
    JButton book, yes, no, back;
    public bookByPlaceConfirm(String trainNumber, String from, String to)
    {
        this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
    }
    public void book() throws IOException
    {
        System.out.println("In book ^^");
        jeff = new JFrame("Booking Confirmation");
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Train Number is:  " + trainNumber);
        System.out.println("From " + from + " to " + to);
        String from1 = from, to1 = to;
        System.out.println("trainNumber " + trainNumber);
        if(ticketCounter.counter(trainNumber, booker.date.getText()) > 0)
        {
            System.out.println("Tickets left " + ticketCounter.counter(trainNumber, booker.date.getText()));
            places = new JLabel("From " + from + " to " + to);
            book = new JButton("Book");
            JLabel nullLabel = new JLabel("");
            ticketsLeft = new JLabel("Tickets Left: " + String.valueOf(ticketCounter.counter(trainNumber, booker.date.getText())));
            trainName = new JLabel(trainData.getTrainName(trainNumber));
            addHash();
            back = new JButton("Back");
            distance = new JLabel("Distance: " + Double.toString(booker.getDistance(from1, to1)) + "   Price: " + Math.ceil(booker.getDistance(from1, to1)*0.64));

            places.setBounds(20, 20, 300, 20);
            book.setBounds(125, 140, 50, 20);
            back.setBounds(20, 140, 50, 20);
            trainName.setBounds(20, 50, 150, 20);
            ticketsLeft.setBounds(20, 80, 150, 20);
            distance.setBounds(20, 110, 300, 20);
            nullLabel.setBounds(50, 50, 60, 20);

            jeff.setSize(300, 200);

            jeff.add(places);
            jeff.add(book);
            jeff.add(trainName);
            jeff.add(ticketsLeft);
            jeff.add(distance);
            jeff.add(back);
            jeff.add(nullLabel);
            jeff.setVisible(true);

            book.addActionListener(this);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jeff.dispose();
                    jeff = null;
                    try {
                        new booker().booker(loginWindow.username.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        else if(ticketCounter.checkIfTrainDateExists(trainNumber, booker.date.getText()) && ticketCounter.counter(trainNumber, booker.date.getText()) == 0)
        {
            System.out.println("Lol no tickets cri about it lol lololololololol");
            JFrame puthiyaJeff = new JFrame("Waiting List");
            JButton yes = new JButton("Yes"), no = new JButton("No");
            JLabel label1 = new JLabel("Seats are not available."), label2 = new JLabel("Do you want to book in the waiting list?"),
                    tempLabel = new JLabel("");

            puthiyaJeff.setSize(300, 200);

            label1.setBounds(10, 20, 250, 20);
            label2.setBounds(10, 50, 300, 20);
            yes.setBounds(20, 100, 50, 20);
            no.setBounds(80, 100, 50, 20);

            puthiyaJeff.add(label1);
            puthiyaJeff.add(label2);
            puthiyaJeff.add(yes);
            puthiyaJeff.add(no);
            puthiyaJeff.add(tempLabel);
            puthiyaJeff.setVisible(true);

            yes.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        new waitingList().queue(loginWindow.username.getText(), from, to, trainNumber, booker.date.getText());
                        puthiyaJeff.dispose();
                        new booker().booker(loginWindow.username.getText());

                    }
                    catch (IOException ex)
                    {
                        System.out.println("Error: " + ex);
                    }
                }
            });

            no.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    puthiyaJeff.dispose();
                    try
                    {
                        new booker().booker(loginWindow.username.getText());
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                }
            });
        }
        else if(!ticketCounter.checkIfTrainDateExists(trainNumber, booker.date.getText()) && ticketCounter.counter(trainNumber, booker.date.getText()) == 0)
        {
            System.out.println("Hello there " + trainNumber);
            BufferedReader brr = new BufferedReader(new FileReader("trainSeats.csv"));
            String s = null;
            String toWrite = "";

            while((s = brr.readLine()) != null)
            {
                if(s.split(",")[0].equals(trainNumber))
                {
                    toWrite += s.split(",")[0] + "," + booker.date.getText();
                    for(int i = 1; i < s.split(",").length; i++)
                    {
                        toWrite += "," + s.split(",")[i];
                    }
                }
            }
            BufferedReader brrrrrrr = new BufferedReader(new FileReader("seats.csv"));
            FileWriter fregly = new FileWriter("seats.csv", true);
            if(brrrrrrr.readLine().isEmpty())
            {
                fregly.append(toWrite);
            }
            else
            {
                fregly.append("\n" + toWrite);
            }
            jeff.dispose();
            jeff = null;
            fregly.close();
            new bookByPlaceConfirm(bookByPlace.stationExists.split("-")[1], bookByPlace.station1, bookByPlace.station2).book();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == book)
        {
            new finalBooker().doMoreOfThis();
            jeff.dispose();
            jeff = null;
        }
    }
    public void addHash()
    {
        System.out.println("Add Hash");
        booker.hash.clear();
        try
        {
            if (new trainStatus().checkTrainNumber(Integer.parseInt(trainNumber)))
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        String place = new trainStatus().getPlace(trainNumber, i + 2);
                        double distance = new trainStatus().getDistance(trainNumber, i + 2);
                        booker.hash.put(place, distance);
                        System.out.println(place + " - " + distance);
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("File not Found");
                    }
                    catch (IOException ex)
                    {
                        System.out.println("IOException");
                    }
                }
            }
            else
            {
                new errorCatch().catcher(2);
            }
        }
        catch (Exception exception)
        {
            try
            {
                new errorCatch().catcher(1);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}