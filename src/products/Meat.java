package products;

public class Meat extends FoodProduct implements Movable{
    private String meatType;

    public Meat(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String meatType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.meatType = meatType;
    }

    @Override
    public void move(int[] location) {

    }
    
}
