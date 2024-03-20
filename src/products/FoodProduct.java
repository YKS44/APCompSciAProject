package products;

public abstract class FoodProduct extends Product{

    protected double expiredPercent;

    public FoodProduct(double price, int quantity, String id, double expirationPercent) {
        super(price, quantity, id);
        this.expiredPercent = expirationPercent;
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

        result += "Percent Expired: " + (expiredPercent) + "%\n";
        result += super.toString();

        return result;
    }
    
}
