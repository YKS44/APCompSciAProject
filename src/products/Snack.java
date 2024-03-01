package products;

public class Snack extends FoodProduct implements Movable{
    private String snackType;

    public Snack(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String snackType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.snackType = snackType;
    }

    @Override
    public boolean move(int[] location) {
        return false;
    }
    
}
