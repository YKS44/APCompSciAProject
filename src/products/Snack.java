package products;

import java.util.Random;

public class Snack extends FoodProduct implements Movable{
    public enum SnackType{
        Chocolate,
        Candy,
        Chips
    }

    private SnackType snackType;

    public Snack(double price, int quantity, String id, double expirationPercent, SnackType snackType) {
        super(price, quantity, id, expirationPercent);
        this.snackType = snackType;
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Snack\n";
        result += "Meat Type: " + snackType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {
        Random rand = new Random();

        return new Snack(1 + rand.nextInt(10), 1 + rand.nextInt(10), Product.generateID(), 5 + rand.nextInt(10), SnackType.values()[rand.nextInt(SnackType.values().length)]);
    }
    
}
