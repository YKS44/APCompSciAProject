package products;

import java.util.Random;

public class Vegetable extends FoodProduct implements Movable{
    public enum VegetableType{
        Carrot,
        Cabbage,
        Cucumber,
        Brocoli
    }

    private VegetableType vegetableType;

    public Vegetable(double price, int quantity, String id, double expirationPercent, VegetableType vegetableType) {
        super(price, quantity, id, expirationPercent);
        this.vegetableType = vegetableType;
    }

    @Override
    public int move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Vegetable\n";
        result += "Meat Type: " + vegetableType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {
        Random rand = new Random();

        return new Vegetable(5 + rand.nextInt(30), 10 + rand.nextInt(10), Product.generateID(), 20 + rand.nextInt(40) - 10, VegetableType.values()[rand.nextInt(VegetableType.values().length)]);
    }
    
}
