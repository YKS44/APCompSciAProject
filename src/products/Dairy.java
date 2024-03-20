package products;

import java.util.Random;

public class Dairy extends FoodProduct implements Movable{
    public enum DairyType{
        Cheese,
        Milk,
        Yogurt,
    }

    private DairyType dairyType;
    private Random rand = new Random();

    public Dairy(double price, int quantity, String id, double expirationPercent, DairyType dairyType) {
        super(price, quantity, id, expirationPercent);
        this.dairyType = dairyType;
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Dairy\n";
        result += "Meat Type: " + dairyType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {

        return new Dairy(5+rand.nextInt(6)-3, 3+rand.nextInt(3), Product.generateID(), rand.nextInt(40)+40, DairyType.values()[rand.nextInt(DairyType.values().length)]);
    }
    
}
