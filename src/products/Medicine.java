package products;

public class Medicine extends Product {
    private String medicineType;

    public Medicine(int[] location, double price, int quantity, String medicineType) {
        super(location, price, quantity);
        this.medicineType = medicineType;
    }

    @Override
    public void purchase() {
        
    }

    @Override
    public double getDiscount() {
        return 0.0;
    }

    @Override
    public boolean move(int[] location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    
}
