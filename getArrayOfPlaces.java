package testFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getArrayOfPlaces
{
    String[] places;
    public void getCount() throws IOException
    {
        BufferedReader brrrr = new BufferedReader(new FileReader("trainDatabase.csv"));
        String s = null;
        int counter = 0;
        while((s = brrrr.readLine()) != null)
        {
            String array[] = s.split(",");
            counter = counter + array.length - 1;
        }
        places = new String[counter];
    }

    public String[] getPlaces() throws IOException
    {
        int i = 0;
        BufferedReader brrrrrrrr = new BufferedReader(new FileReader("trainDatabase.csv"));
        getCount();
        String s = null;
        while((s = brrrrrrrr.readLine()) != null)
        {
            String placesNew[] = s.split(",");
            for(int o = 2; o < placesNew.length; o++)
            {
                places[i] = placesNew[o].split("-")[0];
                i++;
            }
        }
        return places;
    }
}