package testFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class trainStatus {
    static String data[];

    public static String getPlace(String trainNumber, int i) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("trainDatabase.csv"));
        String s = "";
        while ((s = reader.readLine()) != null) {
            data = s.split(",");
            if (trainNumber.equals(data[0])) {
                //11111,AV Express,place01-0,place02-100,place03-180,place04-260,place05-390,place06-500,place07-600,place08-720,place09-850,place010-970
                return data[i].split("-")[0];
            }
        }
        return "Error";
    }

    public double getDistance(String trainNumber, int i) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("trainDatabase.csv"));
        String s = "";
        while ((s = reader.readLine()) != null) {
            String data[] = s.split(",");
            if (trainNumber.equals(data[0])) {
                //11111,AV Express,place01-0,place02-100,place03-180,place04-260,place05-390,place06-500,place07-600,place08-720,place09-850,place010-970
                return Double.parseDouble(data[i].split("-")[1]);
            }
        }
        return 0.0;
    }
    public boolean checkTrainNumber(int trainNumber) throws IOException
    {
        boolean check = false;
        BufferedReader brrr = new BufferedReader(new FileReader("trainDatabase.csv"));
        String str = "";
        while((str = brrr.readLine()) != null)
        {
            int trainNo = Integer.parseInt(str.split(",")[0]);
            if(trainNo == trainNumber)
            {
                check = true;
            }
        }
        return check;
    }
}