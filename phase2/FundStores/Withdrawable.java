package phase2.FundStores;

public interface Withdrawable {
    boolean withdrawFromAccount(double amount);
    boolean withdrawFromATM(int amount);
}
