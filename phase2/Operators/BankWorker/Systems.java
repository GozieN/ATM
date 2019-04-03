package phase2.Operators.BankWorker;

public abstract class Systems{
    //Workers that do not have accounts or access to other users
    //All functionality is to do with fixing machinery in the bank such as ATM
    protected BankManager bm;

    /**
     * set the bm
     * @param bm
     */
    public void setBm(BankManager bm){
        this.bm = bm;
    }

    /**
     *
     * @return returns the bm instance
     */
    public BankManager getBm(){
        return bm;
    }

    /**
     * shuts down the ATM
     */
    public void shutdownATM(){}


    /**
     * starts the ATM machine
     */
    public void startupATM(){
        //starts ATM OUTSIDE of program
    }


}

