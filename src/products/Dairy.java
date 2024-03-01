package products;

public class Dairy extends FoodProduct{
    private String dairyType;

    public Dairy(int[] location, double price, int quantity, String dairyType) {
        super(location, price, quantity, price, price);
        this.dairyType = dairyType;
    }

    @Override
    public boolean move(int[] location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    
}
