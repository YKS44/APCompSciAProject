package products;

import java.util.Random;

public class Meat extends FoodProduct implements Movable{
    public enum MeatType{
        Cow,
        Pig,
        Chicken,
        Sheep
    }

    private MeatType meatType;

    public Meat(double price, int quantity, String id, double expirationPercent, MeatType meatType) {
        super(price, quantity, id, expirationPercent);
        this.meatType = meatType;
    }

    @Override
    public int move() {
        return 0;
    }
    
    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Meat\n";
        result += "Meat Type: " + meatType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {
        Random rand = new Random();

        return new Meat(5+rand.nextInt(6)-3, 3+rand.nextInt(3), Product.generateID(), rand.nextInt(40)+40, MeatType.values()[rand.nextInt(MeatType.values().length)]);
    }
}
