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
        
    }

    @Override
    public double getDiscount() {
        return 0.0;
    }
    
}