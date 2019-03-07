package phase1.Operators;

public interface Operator {

    // highest level interface everything in this package implements this.

    public void singleAccountSummary(Account account);

    public String viewInfo();

    public void viewBalance(Account account);
    }

