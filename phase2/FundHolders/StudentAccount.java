package phase2.FundHolders;
import phase2.Operators.*;

public class StudentAccount extends Debit implements java.io.Serializable {

    public StudentAccount(User accountHolder){super(accountHolder, "StudentAccount"); }
}