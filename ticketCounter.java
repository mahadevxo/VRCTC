package testFiles;

import java.io.*;

public class ticketCounter
{
    public static int getSeatsLeft(String newTrainNumber, String date) throws IOException
    {
        System.out.println("Trainnumber: " + newTrainNumber + "\tDate: " + date + "\t <------");
        BufferedReader brrrr = new BufferedReader(new FileReader("trainSeats.csv"));
        try
        {
            BufferedReader newBrr = new BufferedReader(new FileReader("seats.csv"));

            boolean trainAndDateexists = false;
            String s = "";
            while((s = newBrr.readLine()) != null)
            {
                if(!s.isEmpty())
                {
                    String newTrainnumber = s.split(",")[0];
                    String newDate = s.split(",")[1];
                    if(newTrainnumber.equalsIgnoreCase(newTrainNumber) && newDate.equals(date))
                    {
                        trainAndDateexists = true;
                        break;
                    }
                }
                else
                {
                    break;
                }

            }
            if(!checkIfTrainDateExists(finalBooker.trainNumber, booker.date.getText()))
            {
                newTrainNumber = finalBooker.trainNumber;
                String newS = null;
                while((s = brrrr.readLine())  != null)
                {
                    System.out.println(newTrainNumber + " <-------------------------------> " + s.split(",")[0]);
                    if(s.split(",")[0].equals(newTrainNumber))
                    {
                        System.out.println("Works here");
                        newS = s;
                        break;
                    }
                }
                BufferedReader brrrrrrrrrrrrrrrrr = new BufferedReader(new FileReader("seats.csv"));
                String text = brrrrrrrrrrrrrrrrr.readLine();
                if(text == null || text.isEmpty() || text.isBlank())
                {
                    FileWriter fregly = new FileWriter("seats.csv", true);
                    fregly.append(newS.split(",")[0] + "," + date);
                    for(int i = 1; i < newS.split("-").length; i++)
                    {
                        fregly.append("," + newS.split(",")[i]);
                    }
                    fregly.close();
                }
                else
                {
                    FileWriter fregly = new FileWriter("seats.csv", true);
                    if(!(newS == null))
                    {
                        fregly.append("\n" + newS.split(",")[0] + "," + date);
                        for(int i = 1; i < newS.split("-").length; i++)
                        {
                            fregly.append("," + newS.split(",")[i]);
                        }
                        fregly.close();
                    }
                }

            }
        }
        catch(FileNotFoundException fnf)
        {
            FileWriter fregly = new FileWriter("seats.csv");
            String s = null;
            while((s = brrrr.readLine()) != null)
            {
                String[] data = s.split(",");
                if(newTrainNumber.equals(data[0]))
                {
                    fregly.write(data[0] + "," + date);
                    for(int i = 1; i < data.length; i++)
                    {
                        fregly.write("," + data[i]);
                    }
                }
            }
            fregly.close();
        }
        String s = null;
        int seats = 0;
        brrrr = new BufferedReader(new FileReader("seats.csv"));
        while((s = brrrr.readLine())!= null)
        {
            if(s.split(",")[0].equals(newTrainNumber) && s.split(",")[1].equalsIgnoreCase(date))
            {
                String seatData[] = s.split(",");
                for(int j = 2; j < seatData.length; j++)
                {
                    seats += Integer.parseInt(seatData[j].split("-")[1]);
                }
            }
        }
        brrrr.close();
        return seats;
    }

    public static boolean checkIfTrainDateExists(String trainNumber, String date) throws IOException
    {
        BufferedReader brr = new BufferedReader(new FileReader("seats.csv"));
        String s = null;
        while((s = brr.readLine()) != null)
        {
            String data[] = s.split(",");
            if(data[0].equals(trainNumber) && data[1].equals(date))
            {
                return true;
            }
        }
        return false;
    }

    public static int counter(String trainNumber, String date) throws IOException
    {
        BufferedReader brrr = new BufferedReader(new FileReader("seats.csv"));
        int count = 0;
        String s = null;
        while((s = brrr.readLine()) != null)
        {
            String line[] = s.split(",");
            if(line[0].equals(trainNumber) && line[1].equals(date))
            {
                for(int i = 2; i < line.length; i++)
                {
                    count += Integer.parseInt(line[i].split("-")[1]);
                }
            }
        }
        System.out.println("Count is: " + count);
        return count;
    }
}