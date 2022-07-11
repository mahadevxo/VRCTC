package testFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import static testFiles.loginWindow.username;

public class booker extends Canvas implements ActionListener
{
    public static LinkedHashMap<String, Double> hash = new LinkedHashMap<>();
    public static JLabel[][] stations = new JLabel[10][2];
    public JRadioButton[] radioButtons = new JRadioButton[10];
    JLabel dateLabel = new JLabel("Enter the date to book: ");
    public static JTextField date = new JTextField("YYYY-MM-DD");
    static JFrame jeff = new JFrame();
    JTextField trainSearch;
    JButton searchByNumber, bookByNumber, searchByPlace;
    JButton editProfile, viewTickets, backToLogin, close;
    JComboBox from, to;

    int station1 = 0;
    int station2 = 0;
    public static String trainNumber = null;
    boolean bool = false;

    public void booker(String username) throws IOException
    {
        jeff = new JFrame();
        jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setBounds(400, 10, 300, 20);

        jeff.setTitle("Book Tickets");
        trainSearch = new JTextField("");
        JLabel trainSearchStr = new JLabel("Enter Train Number: ");
        searchByNumber = new JButton("Search");
        trainSearchStr.setBounds(10, 60, 150, 20);
        trainSearch.setBounds(160, 60, 100, 20);
        searchByNumber.setBounds(160, 90, 100 , 20);

        editProfile = new JButton("Edit Profile");
        viewTickets = new JButton("View Tickets");
        backToLogin = new JButton("Back to Login");
        close = new JButton("Exit");

        editProfile.setBounds(400, 300, 130, 20);
        viewTickets.setBounds(400, 340, 130, 20);
        backToLogin.setBounds(400,380,130,20);
        close.setBounds(400, 420, 130, 20);

        dateLabel.setBounds(10, 30, 300, 20);
        jeff.add(dateLabel);

        date.setBounds(160, 30, 100, 20);
        jeff.add(date);

        jeff.add(editProfile);
        jeff.add(viewTickets);
        jeff.add(backToLogin);
        jeff.add(close);
        jeff.add(trainSearchStr);
        jeff.add(trainSearch);
        jeff.add(searchByNumber);
        jeff.add(usernameLabel);

        searchByNumber.addActionListener(this);

        editProfile.addActionListener(this);
        viewTickets.addActionListener(this);
        backToLogin.addActionListener(this);
        close.addActionListener(this);

        getTrainByPlace();

        jeff.setSize(600, 500);

        jeff.setLayout(null);
        jeff.setVisible(true);
        jeff.setResizable(true);
        jeff.setAlwaysOnTop(false);
    }

    public void searchResults() throws IOException {
        if(bool)
        {
            jeff.dispose();
            jeff = null;
            jeff = new JFrame();
            jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Label usernameLabel = new Label("Username: " + username.getText());
            usernameLabel.setBounds(400, 10, 300, 20);

            jeff.setTitle("Book Tickets");
            trainSearch = new JTextField(booker.trainNumber);
            JLabel trainSearchStr = new JLabel("Enter Train Number: ");
            searchByNumber = new JButton("Search");
            trainSearchStr.setBounds(10, 60, 150, 20);
            trainSearch.setBounds(160, 60, 100, 20);
            searchByNumber.setBounds(160, 90, 100 , 20);

            editProfile = new JButton("Edit Profile");
            viewTickets = new JButton("View Tickets");
            backToLogin = new JButton("Back to Login");
            close = new JButton("Exit");

            editProfile.setBounds(400, 300, 130, 20);
            viewTickets.setBounds(400, 340, 130, 20);
            backToLogin.setBounds(400,380,130,20);
            close.setBounds(400, 420, 130, 20);

            dateLabel.setBounds(10, 30, 300, 20);
            jeff.add(dateLabel);

            date.setBounds(160, 30, 100, 20);
            jeff.add(date);

            jeff.add(editProfile);
            jeff.add(viewTickets);
            jeff.add(backToLogin);
            jeff.add(close);
            jeff.add(trainSearchStr);
            jeff.add(trainSearch);
            jeff.add(searchByNumber);
            jeff.add(usernameLabel);

            searchByNumber.addActionListener(this);

            editProfile.addActionListener(this);
            viewTickets.addActionListener(this);
            backToLogin.addActionListener(this);
            close.addActionListener(this);

            getTrainByPlace();

            jeff.setSize(600, 500);

            jeff.setLayout(null);
            jeff.setVisible(true);
            jeff.setResizable(true);
            jeff.setAlwaysOnTop(false);
        }
        bool = true;
        int i = 0;
        int xLabel = 70, xRadio = 40;
        int y = 130;
        for (String s : hash.keySet())
        {
            stations[i][0] = new JLabel("");
            stations[i][1] = new JLabel("");
            stations[i][0].setText("");
            stations[i][0].setText(s);
            stations[i][1].setText((String.valueOf(hash.get(s))));
            System.out.println("----> " + stations[i][0].getText() + " <---> " + stations[i][1].getText());
            radioButtons[i] = new JRadioButton();

            stations[i][0].setBounds(xLabel, y, 300, 20);
            //stations[i][1].setBounds(120, y, 50, 20);
            radioButtons[i].setBounds(xRadio, y, 50, 20);


            stations[i][0].setVisible(true);
            //stations[i][1].setVisible(true);
            jeff.add(stations[i][0]);
            //jeff.add(stations[i][1]);
            jeff.add(radioButtons[i]);

            y += 30;
            i++;
        }
        bookByNumber = new JButton("Book");
        bookByNumber.setBounds(70, 430, 40, 20);
        bookByNumber.addActionListener(this);
        jeff.add(bookByNumber);

        jeff.pack();
        jeff.setSize(600, 500);
        jeff.revalidate();
    }

    public void getTrainByPlace() throws IOException
    {
        JLabel fromPlace = new JLabel("From: "), toPlace = new JLabel("To: ");
        searchByPlace = new JButton("Search");

        String fromPlaceArray[] = new getArrayOfPlaces().getPlaces();
        String toPlaceArray[] = new getArrayOfPlaces().getPlaces();

        from = new JComboBox<>(fromPlaceArray);
        to = new JComboBox<>(toPlaceArray);

        from.setBounds(400, 40, 150, 20);
        to.setBounds(400, 70, 150, 20);
        searchByPlace.setBounds(400, 100, 70, 20);
        fromPlace.setBounds(350, 40, 50, 20);
        toPlace.setBounds(350, 70, 50, 20);

        searchByPlace.addActionListener(this);
        jeff.add(fromPlace);
        jeff.add(toPlace);
        jeff.add(from);
        jeff.add(to);
        jeff.add(searchByPlace);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == searchByNumber)
        {
            System.out.println("In search action event");
            hash.clear();
            trainNumber = trainSearch.getText().trim();
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
                            hash.put(place, distance);
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
            try {
                searchResults();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == bookByNumber)
        {
            jeff.dispose();
            jeff = null;
            System.out.println("in book");
            station1 = 0;
            station2 = 0;
            for (int i = 0; i < radioButtons.length; i++)
            {
                if (radioButtons[i].isSelected())
                {
                    station1 = i+1;
                    for (int j = i + 1; j < radioButtons.length; j++)
                    {
                        if(radioButtons[j].isSelected())
                        {
                            station2 = j+1;
                            break;
                        }
                    }
                    break;
                }
            }
            System.out.println("Station 1: " + station1);
            System.out.println("Station 2: " + station2);
            try
            {
                (new finalBooker()).finalBooker(station1, station2);
            }
            catch (IOException ex)
            {
                System.out.println("Error");
            }
        }
        else if(e.getSource() == searchByPlace)
        {
            System.out.println("Hello there");
            try
            {
                System.out.println(from.getSelectedItem().toString() + "\t" + to.getSelectedItem());
                new bookByPlace(from.getSelectedItem().toString(), to.getSelectedItem().toString());
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == editProfile)
        {
            jeff.dispose();
            jeff = null;
            (new updateProfile()).updater();
        }
        else if(e.getSource() == viewTickets)
        {
            jeff.dispose();
            jeff = null;
            try
            {
                new ticketView().view();
            }
            catch(Exception exception)
            {
                System.out.println("Error: " + exception);
            }
        }
        else if(e.getSource() == backToLogin)
        {
            jeff.dispose();
            jeff = null;
            try
            {
                new loginWindow();
            }
            catch (IOException ex)
            {
                System.out.println("Error: " + ex);
            }
        }
        else if(e.getSource() == close)
        {
            System.exit(0);
        }
    }
    public static double getDistance(String st1, String st2)
    {
        Double st1D = hash.get(st1);
        Double st2D = hash.get(st2);
        return (Math.abs(st1D-st2D));
    }
}