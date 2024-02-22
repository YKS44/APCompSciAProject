package products;

public class Medicine extends Product implements Movable{
    private String medicineType;

    public Medicine(int[] location, double price, int quantity, String medicineType) {
        super(location, price, quantity);
        this.medicineType = medicineType;
    }

    @Override
    public void move(int[] location) {

    }

    @Override
    public void purchase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'purchase'");
    }

    @Override
    public double getDiscount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiscount'");
    }
    
}
