package products;

public abstract class FoodProduct extends Product{

    protected double expirationNum;
    protected double expirationRate;

    public FoodProduct(int[] location, double price, int quantity, double expirationNum, double expirationRate) {
        super(location, price, quantity);
        this.expirationNum = expirationNum;
        this.expirationRate = expirationRate;
    }

    @Override
    public void purchase() {

    }

    @Override
    public double getDiscount() {
        return 0.0; 
    }
    
}
