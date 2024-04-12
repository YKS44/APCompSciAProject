package products;

/**
 * A subclass of the {@link Product} class. Contains extra parameters such as the expired percent.
 */
public abstract class FoodProduct extends Product{

    protected double expiredPercent;

    public FoodProduct(double price, int quantity, String id, double expirationPercent) {
        super(price, quantity, id);
        this.expiredPercent = expirationPercent;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Percent Expired: " + (expiredPercent) + "%\n";
        result += super.toString();

        return result;
    }
    
}
