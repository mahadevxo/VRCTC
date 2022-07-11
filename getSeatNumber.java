package testFiles;

import java.io.*;

public class getSeatNumber
{
    public static String getSeat(String date) throws IOException
    {
        String coach = null, seat = null;
        BufferedReader newbrr = new BufferedReader(new FileReader("seats.csv"));

        System.out.println(date);

        String info = null;

        System.out.println("In getSeat()");

        while ((info = newbrr.readLine()) != null)
        {
            //55555,D1-20,D2-20,D3-20,C1-10,C2-10
            System.out.println(info);
            if ((info.split(",")[0].equals(booker.trainNumber) || info.split(",")[0].equals(bookByPlaceConfirm.trainNumber)) && info.split(",")[1].equals(date))
            {
                //55555     d1-20       d2-20       d3-20...
                for (int j = 2; j < info.split(",").length; j++)
                {
                    coach = info.split(",")[j].split("-")[0];
                    seat = info.split(",")[j].split("-")[1];
                    if (Integer.parseInt(seat) == 0)
                    {
                        continue;
                    }
                    else if (Integer.parseInt(seat) > 0)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Error? tf");
                    }
                }
                System.out.println(coach + " lol " + seat);
                return coach + " " + seat;
            }
        }
        return null;
    }

    public void reduceNumberOfTickets(String seatNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        //11111,D1-10,D2-10,D3-10,C1-10,C2-10
        System.out.println("Seat-->" + seatNumber); //D1 10
        String coachAndSeat[] = seatNumber.split(" ");//[D1] [10]
        String data = null;
        String dataInFile = "";
        String trainNumber = finalBooker.trainNumber; //11111
        System.out.println("Train numberr ---> " + trainNumber);
        System.out.println("Reducing numberoftickers");

        if (ticketCounter.getSeatsLeft(finalBooker.trainNumber, booker.date.getText()) == 0)
        {
            System.out.println("no more tickets finally");
        }
        else
        {
            while ((data = reader.readLine()) != null) {
                String line[] = data.split(",");
                System.out.println(data + " <----------------");
                if (line[0].equals(trainNumber) && line[1].equals(booker.date.getText())) {
                    if (dataInFile.isEmpty()) {
                        dataInFile += trainNumber + "," + booker.date.getText();
                    } else {
                        dataInFile += "\n" + trainNumber + "," + booker.date.getText();
                    }
                    for (int i = 2; i < line.length; i++) {
                        if (line[i].split("-")[0].equals(coachAndSeat[0])) {
                            System.out.println("-----> || " + line[i]);
                            if (Integer.parseInt(line[i].split("-")[1]) > 0) {
                                line[i] = line[i].split("-")[0] + "-" + String.valueOf(Integer.parseInt(line[i].split("-")[1]) - 1);
                                dataInFile += "," + line[i];
                            } else if (Integer.parseInt(line[i].split(",")[1]) == 0) {
                                dataInFile += "," + line[i];
                            }
                        } else {
                            dataInFile += "," + line[i];
                        }
                    }
                } else {
                    if (dataInFile.isEmpty()) {
                        dataInFile += data;
                    } else {
                        dataInFile += "\n" + data;
                    }
                }
            }

            System.out.println(dataInFile + "\thuh what is this");
            FileWriter writer = new FileWriter("seats.csv");
            writer.write(dataInFile);
            writer.close();
        }
    }
}