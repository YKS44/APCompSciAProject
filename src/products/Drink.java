package products;

public class Drink extends FoodProduct implements Movable{

    private String drinkType;

    public Drink(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String drinkType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.drinkType = drinkType;
    }

    @Override
    public void move(int[] location) {

    }
    
}
