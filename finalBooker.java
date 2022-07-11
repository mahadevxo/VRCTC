package testFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class finalBooker implements ActionListener
{
    static JFrame jeff = new JFrame();
    JButton book = new JButton("Book");
    JButton goBack = new JButton("Go back");
    String station1Name, station2Name;
    static String stationNamefinal1;
    static String stationNamefinal2;
    static String trainNumber;
    JLabel distanceLabel = new JLabel("Distance: "), distance = new JLabel("");

    public void finalBooker(int station1, int station2) throws IOException
    {
        if(trainNumber == null)
        {
            if(bookByPlaceConfirm.trainNumber == null)
            {
                trainNumber = booker.trainNumber;
            }
            else
            {
                trainNumber = bookByPlaceConfirm.trainNumber;
            }
        }
        jeff = new JFrame();
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String user = loginWindow.username.getText();
        System.out.println(user);
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jeff.setTitle("Ticket Conformation");
        jeff.setSize(300, 275);
        jeff.setVisible(true);
        System.out.println(station1 + "<>" + station2);
        station1Name = getNameOfStation(station1);
        station2Name = getNameOfStation(station2);

        Label username = new Label("Username:\t" + user);
        Label trainName = new Label("Train Name:\t" + trainStatus.data[1]);
        JLabel fromPlace = new JLabel("From " + station1Name + " to " + station2Name);
        distance.setText(Double.toString(booker.getDistance(station1Name, station2Name)) + "   Cost: " + Math.ceil(booker.getDistance(station1Name, station2Name) * 0.64));
        JLabel nullLabel = new JLabel("");

        int ticketsLeft = new ticketCounter().getSeatsLeft(booker.trainNumber, booker.date.getText());

        System.out.println(ticketsLeft);
        JLabel numberOfTicketsLeft = new JLabel("Number of tickets Left: " + (ticketsLeft));

        username.setBounds(10, 10, 200, 20);
        trainName.setBounds(10, 40, 300, 20);
        fromPlace.setBounds(10, 70, 400, 20);
        numberOfTicketsLeft.setBounds(10, 100, 400, 20);
        book.setBounds(125, 170, 50, 20);
        goBack.setBounds(100, 210, 100, 20);
        distanceLabel.setBounds(10, 130, 100, 20);
        distance.setBounds(110, 130, 300, 20);
        nullLabel.setBounds(10, 70, 100, 20);

        book.addActionListener(this);
        goBack.addActionListener(this);

        jeff.add(username);
        jeff.add(trainName);
        jeff.add(fromPlace);
        jeff.add(numberOfTicketsLeft);
        jeff.add(book);
        jeff.add(goBack);
        jeff.add(distance);
        jeff.add(distanceLabel);
        jeff.add(nullLabel);

        jeff.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String seat = null;
        if (e.getSource() == book)
        {
            doMoreOfThis();
        }
        else if(e.getSource() == goBack)
        {
            jeff.dispose();
            try
            {
                new booker().booker(loginWindow.username.getText());
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void doMoreOfThis()
    {
        String seat = "";
        try
        {
            seat = getSeatNumber.getSeat(booker.date.getText());

            System.out.println(seat + "<--- seat");
            FileWriter fow = new FileWriter("bookingDatabase.csv", true);

            BufferedReader brrrr = new BufferedReader(new FileReader("bookingDatabase.csv"));
            boolean isEmpty = false;
            String s = brrrr.readLine();
            if ((s  == null) || s.isEmpty())
            {
                isEmpty = true;
            }

            if(station2Name == null || station1Name == null)
            {
                System.out.println("HAH FOUND YOU");
                station1Name = bookByPlaceConfirm.from;
                station2Name = bookByPlaceConfirm.to;
            }
            stationNamefinal1 = station1Name;
            stationNamefinal2 = station2Name;
            if(bookByPlaceConfirm.trainNumber == null)
            {
                trainNumber = booker.trainNumber;
            }
            else
            {
                trainNumber = bookByPlaceConfirm.trainNumber;
            }
            if (isEmpty && booker.trainNumber != null && ticketCounter.getSeatsLeft(booker.trainNumber, booker.date.getText()) > 0)
                {
                    System.out.println("book case 1");
                    assert seat != null;
                    String seatData = loginWindow.username.getText() + "," + station1Name + "," +
                            station2Name + "," + trainStatus.data[1] + "," + trainStatus.data[0] + "," + booker.date.getText() + "," +
                            seat.split(" ")[0] + "-" + seat.split(" ")[1];
                    System.out.println(seat +" tits " + seatData);
                    fow.write(seatData);

                    isEmpty = false;

                    new getSeatNumber().reduceNumberOfTickets(seat);
                    jeff.revalidate();
                    jeff.dispose();
                    new booker().booker(loginWindow.username.getText());
                }
                else if(!isEmpty && booker.trainNumber != null && ticketCounter.getSeatsLeft(booker.trainNumber, booker.date.getText()) > 0)
                {
                    System.out.println("book case 2");
                    assert seat != null;
                    String seatData = loginWindow.username.getText() + "," + station1Name + "," +
                            station2Name + "," + trainStatus.data[1] + "," + trainStatus.data[0] +  "," + booker.date.getText() + "," +
                            seat.split(" ")[0] + "-" + seat.split(" ")[1];
                    System.out.println(seat +" tits " + seatData);
                    fow.write("\n" + seatData);

                    new getSeatNumber().reduceNumberOfTickets(seat);
                    jeff.revalidate();
                    jeff.dispose();
                    new booker().booker(loginWindow.username.getText());
                }
                else if(isEmpty && ticketCounter.getSeatsLeft(bookByPlaceConfirm.trainNumber, booker.date.getText()) > 0)
                {
                    System.out.println("this works lol");
                    System.out.println("book case 1");
                    assert seat != null;
                    String seatData = loginWindow.username.getText() + "," + station1Name + "," +
                            station2Name + "," + trainData.getTrainName(bookByPlaceConfirm.trainNumber) + "," + bookByPlaceConfirm.trainNumber + "," + booker.date.getText() + "," +
                            seat.split(" ")[0] + "-" + seat.split(" ")[1];
                    System.out.println(seat +" tits " + seatData);
                    fow.write(seatData);

                    isEmpty = false;

                    new getSeatNumber().reduceNumberOfTickets(seat);
                    jeff.revalidate();
                    jeff.dispose();
                    new booker().booker(loginWindow.username.getText());

                }
                else if(!isEmpty && ticketCounter.getSeatsLeft(bookByPlaceConfirm.trainNumber, booker.date.getText()) > 0)
                {
                    System.out.println("This works again lol");
                    System.out.println("book case 2");
                    assert seat != null;
                    String seatData = loginWindow.username.getText() + "," + station1Name + "," +
                            station2Name + "," + trainData.getTrainName(bookByPlaceConfirm.trainNumber) + "," + bookByPlaceConfirm.trainNumber +  "," + booker.date.getText() + "," +
                            seat.split(" ")[0] + "-" + seat.split(" ")[1];
                    System.out.println(seat +" tits " + seatData);
                    fow.write("\n" + seatData);

                    new getSeatNumber().reduceNumberOfTickets(seat);
                    jeff.revalidate();
                    jeff.dispose();
                    new booker().booker(loginWindow.username.getText());
                }
                else
                {
                   System.out.println("Tickets theernu");
                   booker.jeff.dispose();
                   finalBooker.jeff.dispose();

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
                                new waitingList().queue(loginWindow.username.getText(), finalBooker.stationNamefinal1, finalBooker.stationNamefinal2, finalBooker.trainNumber, booker.date.getText());
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
            fow.flush();
            fow.close();


        }
        catch (IOException ex)
        {
            System.out.println("Error ::" + ex);
        }
    }

    public static String getNameOfStation(int stationNumber)
    {
        System.out.println("in getNameOfStation, station number: " + stationNumber);
        String s = "";
        int i = 0;
        for (String s1 : booker.hash.keySet())
        {
            if (i < 10)
            {
                if (i == stationNumber - 1)
                {
                    s = s1;
                    i = 0;
                    break;
                }
                else
                {
                    i += 1;
                }
            }
        }
        return s;
    }
}