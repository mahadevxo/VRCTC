package testFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class trainData
{
    public static String getTrainName(String trainNumber) throws IOException
    {
        BufferedReader brrrrrrr = new BufferedReader(new FileReader("trainDatabase.csv"));
        String s;
        while((s = brrrrrrr.readLine()) != null)
        {
            String data[] = s.split(",");
            if(data[0].equals(trainNumber))
            {
                return data[1];
            }
        }
        return "";
    }

    public static void removeAndAdd(String trainNumber, String date) throws IOException
    {
        BufferedReader brrrr = new BufferedReader(new FileReader("seats.csv"));
        String s = null;
        String toWrite = "";
        while((s = brrrr.readLine()) != null)
        {
            if(s.split(",")[0].equals(trainNumber) && s.split(",")[1].equals(date))
            {
                String data[] = s.split(",");
                String moreData[] = data[2].split("-");
                data[2] = data[2].split("-")[0] + "-" + String.valueOf(Integer.parseInt(moreData[1]) + 1);
                for(int i = 0; i < data.length; i++)
                {
                    if(toWrite.isEmpty())
                    {
                        if(i == 0)
                        {
                            toWrite += data[i];
                        }
                        else
                        {
                            toWrite += "," + data[i];
                        }
                    }
                    else
                    {
                        if(i == 0)
                        {
                            toWrite += "\n" + data[i];
                        }
                        else
                        {
                            toWrite += "," + data[i];
                        }
                    }
                }
            }
            else
            {
                if(toWrite.isEmpty())
                {
                    toWrite+=s;
                }
                else
                {
                    toWrite += "\n" + s;
                }
            }
        }
        FileWriter fregly = new FileWriter("seats.csv");
        fregly.write(toWrite);
        fregly.close();
    }
}
