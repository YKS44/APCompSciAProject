package administration;

public class Customer implements Account{

    private double moneyLeft;
    private AccountLevel level;

    public Customer(double moneyLeft){
        this.moneyLeft = moneyLeft;
        level = AccountLevel.CUSTOMER;
    }

    /**
     * 
     * @return The money left for the customer
     */
    public double getMoneyLeft(){
        return moneyLeft;
    }

    /**
     * Subtracts the money left in the account by the amount.
     * 
     */
    public void reduceMoney(double amount){
        moneyLeft -= amount;
    }

    @Override
    public AccountLevel getAccountLevel() {
        return level;
    }
}


