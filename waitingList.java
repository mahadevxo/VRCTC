package testFiles;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.io.*;

public class waitingList
{
    public void queue(String username, String station1, String station2, String trainNumber, String date) throws IOException
    {
        try
        {
            BufferedReader brrr = new BufferedReader(new FileReader("waitingList.csv"));
            String data = brrr.readLine();
            String text = username + "," + trainNumber + "," + date + "," + station1 + "," + station2 + "," + getCounter(trainNumber);
            if (data == null || data.isEmpty())
            {
                FileWriter writer = new FileWriter("waitingList.csv", true);
                writer.append(text);
                writer.close();
            }
            else
            {
                FileWriter writer = new FileWriter("waitingList.csv", true);
                writer.append("\n" + text);
                writer.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            FileWriter writer = new FileWriter("waitingList.csv");
            writer.close();
            queue(username, station1, station2, trainNumber, date);
        }
    }
    public static String pop(String trainNumber, String date) throws IOException
    {
        BufferedReader brrrr = new BufferedReader(new FileReader("waitingList.csv"));
        String data = brrrr.readLine(), dataToBeWrittern = "";
        String s = null;
        while((s = brrrr.readLine()) != null)
        {
            if(s.split(",")[1].equals(trainNumber) && s.split(",")[2].equals(date))
            if (dataToBeWrittern.isEmpty())
            {
                dataToBeWrittern += s;
            }
            else
            {
                dataToBeWrittern += "\n" + s;
            }
        }
        FileWriter writer = new FileWriter("waitingList.csv");
        writer.write(dataToBeWrittern);
        writer.flush();
        writer.close();
        reduceCount(trainNumber, date);
        return data;
    }

    public int getCounter(String trainNumber) throws IOException
    {
        BufferedReader brrrrrrrr = new BufferedReader(new FileReader("waitingList.csv"));
        int counter = 1;
        String s = null;
        while((s = brrrrrrrr.readLine()) != null)
        {
            if(s.split(",")[1].equals(trainNumber))
            {
                counter++;
            }
            else
            {
                continue;
            }
        }
        return counter;
    }
    public static void reduceCount(String trainNumber, String date) throws IOException
    {
        String s = null;
        int counter = 1;
        String dataToBeWritten = "";
        BufferedReader brr = new BufferedReader(new FileReader("waitingList.csv"));
        while((s = brr.readLine()) != null)
        {
            String data[] = s.split(",");
            if(data[2].equals(date))
            {
                data[5] = Integer.toString(counter);
                if(dataToBeWritten.isEmpty())
                {
                    dataToBeWritten += data[0];
                    for(int i = 1; i < data.length; i++)
                    {
                        dataToBeWritten += "," + data[i];
                    }
                }
                else
                {
                    dataToBeWritten += "\n" + data[0];
                    for(int i = 1; i < data.length; i++)
                    {
                        dataToBeWritten += "," + data[i];
                    }
                }
                counter++;
            }
        }
        FileWriter fregly = new FileWriter("waitingList.csv");
        fregly.write(dataToBeWritten);
        fregly.close();
    }
    public void WaitingList() throws IOException
    {
        System.out.println("WAITINGLIST");
        queue(loginWindow.username.getText(), finalBooker.stationNamefinal1, finalBooker.stationNamefinal2, finalBooker.trainNumber, booker.date.getText());
    }
    public static void addTicket(String popped, String seat) throws IOException
    {
        FileWriter fregly = new FileWriter("bookingDatabase.csv", true);
        String data[] = popped.split(",");
        String write = data[0] + "," + data[3] + "," + data[4] + "," + trainData.getTrainName(data[1]) + "," + data[1]+ "," + data[2] + "," + seat;
        fregly.append("\n" + write);
        fregly.close();
    }
    public static boolean doesWaitingListExist(String trainNumber, String date) throws IOException
    {
        System.out.println("Train number: " + trainNumber + "Date: " + date);

        BufferedReader brrrr = new BufferedReader(new FileReader("waitingList.csv"));
        String s = "";
        while((s = brrrr.readLine()) != null)
        {
            if(s.split(",")[1].equals(trainNumber) && s.split(",")[2].equals(date))
            {
                return true;
            }
        }
        return false;
    }
}
