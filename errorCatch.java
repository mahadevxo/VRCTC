package testFiles;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class errorCatch {
    JFrame jeff = new JFrame();

    public void catcher(int errorCode) throws IOException
    {
        jeff.setTitle("Error Window");
        /*
        1 -> General Error
        2 -> trainNumber does not exist within database
        3 -> no more tickets left
         */
        switch (errorCode)
        {
            case 1 -> case1();
            case 2 -> case2();
            case 3 -> case3();
            case 4 -> case4();
        }
    }

    static void case1()
    {
        System.out.println("Case 1 --> General Error");
    }

    static void case2()
    {
        System.out.println("Case 2 --> trainNumber does not exist within database");
    }

    static void case3() throws IOException
    {
        System.out.println("Case 3 --> No more tickets");
    }

    static void case4()
    {
        System.out.println("Fatal error");
        System.exit(0);
    }

    public void fileExceptionCatcher(String fileName) throws IOException
    {
        FileWriter fow = new FileWriter(fileName);
        fow.close();
    }
}