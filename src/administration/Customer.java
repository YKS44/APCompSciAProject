package administration;

public class Customer implements Account{

    private double moneyLeft;

    public Customer(double moneyLeft){
        this.moneyLeft = moneyLeft;
    }

    public double getMoneyLeft(){
        return moneyLeft;
    }

    @Override
    public void move() {
        
    }

    @Override
    public void addItem(int[][] loc) {
        
    }

    @Override
    public void removeItem(int[][] loc) {
        
    }

    @Override
    public void setPrice(double price) {
        
    }
    
}
