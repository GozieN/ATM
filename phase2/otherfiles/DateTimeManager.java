package phase2.otherfiles;

 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.Timer;
 import java.util.TimerTask;

public class DateTimeManager {
    Timer tim = new Timer();

    TimerTask updateSeconds = new TimerTask() {
        @Override
        public void run() {
            String now = new String(getLastLine());
            String updated = "";
            if(now.substring(15,17).equals("59")){
                if(now.substring(13,15).equals("59")){
                    if(now.substring(15,17).equals("23")) {
                        String time = new String("00:00:00");
                        if((now.substring(2, 4).equals("12")) && (now.substring(0, 2).equals("31"))){
                            int year = Integer.parseInt(now.substring(4, 8));
                            year += 1;
                            String date = new String( "0101" + year);
                            updated = time + " " + date;
                            //write to file
                        }else if(((now.substring(2, 4).equals("10")) || (now.substring(2, 4).equals("08")) || (now.substring(2, 4).equals("07")) || (now.substring(2, 4).equals("05")) || (now.substring(2, 4).equals("03")) ||((now.substring(2, 4).equals("01")))) && ((now.substring(0, 2).equals("31")))){
                            int month = Integer.parseInt(now.substring(2, 4));
                            month += 1;
                            if(month < 10){
                                String date = new String("010" + month + now.substring(4,8));
                                updated = date + " " + time;
                                // write to file
                            }else{
                                String date = new String("01" + month + now.substring(4,8));
                                //write to file
                            }
                        }else if(((now.substring(2, 4).equals("04")) || (now.substring(2, 4).equals("06")) || (now.substring(2, 4).equals("09")) || (now.substring(2, 4).equals("11"))) && ((now.substring(0, 2).equals("30")))){
                            int month = Integer.parseInt(now.substring(2, 4));
                            month += 1;
                            if(month < 10){
                                String date = new String("010" + month + now.substring(4,8));
                                updated = date + " " + time;
                                // write to file
                            }else{
                                String date = new String("01" + month + now.substring(4,8));
                                //write to file
                            }
                        }else if(now.substring(2, 4).equals("02") && (now.substring(0, 2).equals("28"))){
                            updated = "0103" + now.substring(4, 8) + " " + time;
                            //write to file
                        }
                    else{
                        int hour = Integer.parseInt(updated.substring(10, 13));
                        hour += 1;
                        if (hour < 10) {
                            updated = now.substring(0, 9) + "0" + hour + ":00:00";
                            //write to file
                        }else
                            updated = now.substring(0, 9) + hour + ":00:00";
                        }
                    }
                }

            }
        }
    };

    public String getLastLine() {
        String currLine;
        String lastLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/date.txt"));

            while ((currLine = br.readLine()) != null) {
                lastLine = currLine;
            }
        } catch (IOException e) {
        }
        return lastLine;
    }
}
