package products;

public class Vegetable extends FoodProduct{
    private String vegetableType;

    public Vegetable(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String vegetableType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.vegetableType = vegetableType;
    }
    
}
