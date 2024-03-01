package products;

public class Fish extends FoodProduct implements Movable{
    private String fishType;

    public Fish(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String fishType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.fishType = fishType;
    }

    @Override
    public boolean move(int[] location) {
       System.out.println("This product is not movable");
       return false;
    }
    
}
