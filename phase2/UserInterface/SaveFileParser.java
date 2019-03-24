package phase2.UserInterface;

import phase2.Operators.User;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import phase2.FundStores.*;
import phase2.FundStores.Debt.*;
import phase2.FundStores.Asset.*;




public class SaveFileParser {
    private Pattern fileStart=Pattern.compile("^Account and User List:");
    private Pattern accsStart=Pattern.compile("^Accounts:");
    private Pattern accInfo=Pattern.compile("(ChequingAccount|Credit|Debit)\\s(\\d+)\\s(\\w+)\\s(\\d+)\\.?(\\d+)");
    private Pattern endAccs=Pattern.compile("End Accounts");
    private Pattern userStart=Pattern.compile("Users:");
    private Pattern usersInfo=Pattern.compile("\\w+\\s\\w+");
    private Pattern usersEnd= Pattern.compile("End Users:");
    private Pattern endFile=Pattern.compile("End of List");

    public boolean parse(BufferedReader input){ //GOING TO NEED ACCESS TO GUI OR MODEL TO SEND DATA
        ArrayList<User> listUsers= new ArrayList<User>();
        ArrayList<Account> listAccounts=new ArrayList<Account>();
        boolean retVal=true;
        try{
            int state=0; Matcher m; String line;
            while ((line=input.readLine())!=null){
                switch(state){
                    case 0:
                        m=fileStart.matcher(line);
                        if (m.matches()){
                            state=1; break;
                        }
                        else{
                            state=20; break;
                        }
                    case 1:
                        m=accsStart.matcher(line);
                        if (m.matches()){
                            state=2; break;
                        }else{
                            state=20; break;
                        }
                    case 2:
                        m=accInfo.matcher(line);
                        if (m.matches()){
                            String accType=m.group(1);
                            String[] splitted=line.split("\\s+");
                            if (accType=="ChequingAccount"){
                                ChequingAccount acc=new ChequingAccount(Integer.parseInt(splitted[1]), splitted[2], Double.parseDouble(splitted[3]));
                            }else if(accType=="Credit"){

                            }else if(accType=="Debit"){

                            }
                        }
                }
            }
        }catch (Exception e){
            return false;
        }
        return retVal;
    }
}
