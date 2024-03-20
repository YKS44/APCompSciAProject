package products;

import java.util.Random;

public class Fish extends FoodProduct{
    public enum FishType{
        Cod,
        Salmon,
        Snake,
        Crab
    }

    private FishType fishType;

    public Fish(double price, int quantity, String id, double expirationPercent, FishType fishType) {
        super(price, quantity, id, expirationPercent);
        this.fishType = fishType;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Fish\n";
        result += "Meat Type: " + fishType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {
        Random rand = new Random();

        return new Fish(5+rand.nextInt(6)-3, 3+rand.nextInt(3), Product.generateID(), rand.nextInt(40)+40, FishType.values()[rand.nextInt(FishType.values().length)]);
    }

    @Override
    public int move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    
}
