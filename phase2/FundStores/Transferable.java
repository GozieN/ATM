package phase2.FundStores;

public interface Transferable {
    boolean withdrawFromAccount(double amount);
    boolean withdrawFromATM(int amount);
    boolean transfer(double amount, Account receiverAccount);
}
