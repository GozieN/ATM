package phase1.Operators;

// highest level Operators interface - everything in this package implements this
public interface Operator {

    public void singleAccountSummary(Account account);

    public void viewInfo();

    public void viewBalance(Account account);
}