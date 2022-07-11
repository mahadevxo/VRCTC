package testFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class databaseChecker
{
    //1 if username is true and password is true
    //2 if username is true but password is false
    //3 if username is false, then ask for registration
    //0 if error
    public static boolean check(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("loginDatabase.csv"));
        String str = null;
        boolean usernameExists = false, credCheck = false;
        while ((str = reader.readLine()) != null)
        {
            String data[] = str.split(",");
            if(data.length > 1)
            {
                System.out.println(data[0] + "::" + data[1]);
                if ((data[0].equals(username)) && (data[1].equals(password)))
                {
                    System.out.println("Username and password is correct");
                    usernameExists = true;
                    credCheck = true;
                    break;
                }
                else if (data[0].equals(username))
                {
                    if (!data.equals(password))
                    {
                        System.out.println("Username is correct but password is wrong");
                        usernameExists = true;
                        credCheck = false;
                    }
                }
                else if (!usernameExists)
                {
                    System.out.println("Username does not exist in database");
                }
            }
        }
        return credCheck;
    }
}