package testFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class bookByPlace implements ActionListener
{
    static String station1, station2;
    JButton searchByPlace;
    JLabel label = new JLabel();
    static String stationExists = null;


    public bookByPlace(String station1, String station2) throws IOException
    {
        System.out.println("does it work here ----> " + station1);
        bookByPlace.station1 = station1;
        bookByPlace.station2 = station2;
        searchByPlace = new JButton("Search");
        searchByPlace.addActionListener(this);
        booker();
    }

    public void booker() throws IOException
    {
        System.out.println("in booker");
        BufferedReader reader = new BufferedReader(new FileReader("trainDatabase.csv"));
        System.out.println(station1 + "\t" + station2);

        String str = null, data[] = null;
        while((str = reader.readLine()) != null)
        {
            data = str.split(",");
            stationExists = findStation(data, station1, station2);
            if(stationExists.split("-")[0].equals("true"))
            {
                break;
            }
        }

        if(stationExists.split("-")[0].equals("true"))
        {
            System.out.println("General Kenobi " + stationExists.split("-")[1]);
            booker.jeff.dispose();
            new bookByPlaceConfirm(stationExists.split("-")[1], station1, station2).book();
        }
        else
        {
            System.out.println("nu train, cri about it");
            label.setText("Train does not exist");
            label.setBounds(400, 150,200, 20);
            booker.jeff.add(label);
            booker.jeff.pack();
            booker.jeff.setSize(600, 500);
            booker.jeff.revalidate();
        }
    }
    public static String findStation (String[] data, String station1, String station2)
    {
        boolean s1 = false, s2 = false;
        String trainNumber = "";
        for(int i = 2; i < data.length; i++)
        {
            if(data[i].split("-")[0].equals(station1))
            {
                s1 = true;
                trainNumber = data[0];
            }
            else if(data[i].split("-")[0].equals(station2))
            {
                s2 = true;
                trainNumber = data[0];
            }
            else if(s1 && s2)
            {
                break;
            }
        }
        if(s1 && s2)
        {
            return "true-" + trainNumber;
        }
        return "false-null";
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == searchByPlace)
        {
            System.out.println("searchByPlace");
        }
    }
}