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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void addItem(int[][] loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public void removeItem(int[][] loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    @Override
    public void setPrice(double price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrice'");
    }
    
}
