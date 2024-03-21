package products;

import java.util.Random;

public class Drink extends FoodProduct implements Movable{

    public enum DrinkType{
        Water,
        Soda,
        SoftDrink,
        Tea
    }

    private DrinkType drinkType;
    private Random rand = new Random();

    public Drink(double price, int quantity, String id, double expirationPercent, DrinkType drinkType) {
        super(price, quantity, id, expirationPercent);
        this.drinkType = drinkType;
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Drink\n";
        result += "Drink Type: " + drinkType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {

        return new Drink(3+rand.nextInt(6), 3+rand.nextInt(3), Product.generateID(), 0, DrinkType.values()[rand.nextInt(DrinkType.values().length)]);
    }
    
}
