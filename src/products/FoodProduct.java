package products;

public abstract class FoodProduct extends Product{

    protected double expiredPercent;
    protected double expirationRate;

    public FoodProduct(int[] location, double price, int quantity, double expirationPercent, double expirationRate) {
        super(location, price, quantity);
        this.expiredPercent = expirationPercent;
        this.expirationRate = expirationRate;
    }

    @Override
    public void purchase() {

    }

    @Override
    public double getDiscount() {
        return 0.0; 
    }

    @Override
    public String toString(){
        String result = "";

        result += "Expiration Percent: " + (expiredPercent*100) + "%\n";
        result += super.toString();

        return result;
    }
    
}
