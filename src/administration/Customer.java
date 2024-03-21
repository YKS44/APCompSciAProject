package administration;

public class Customer implements Account{

    private double moneyLeft;
    private AccountLevel level;

    public Customer(double moneyLeft){
        this.moneyLeft = moneyLeft;
        level = AccountLevel.CUSTOMER;
    }

    public double getMoneyLeft(){
        return moneyLeft;
    }

    public void reduceMoney(double amount){
        moneyLeft -= amount;
    }

    @Override
    public AccountLevel getAccountLevel() {
        return level;
    }
}


