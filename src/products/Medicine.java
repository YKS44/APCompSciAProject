package products;

import java.util.Random;

public class Medicine extends Product implements Movable{
    public enum MedicineType{
        Sleep,
        Sickness,
        Depressants
    }

    private MedicineType medicineType;

    public Medicine(double price, int quantity, String id, MedicineType medicineType) {
        super(price, quantity, id);
        this.medicineType = medicineType;
    }

    @Override
    public int move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Medicine\n";
        result += "Medicine Type: " + medicineType.toString() + "\n";
        result += super.toString();

        return result;
    }

    @Override
    public Product generateProduct() {
        Random rand = new Random();

        return new Medicine(10 + rand.nextInt(30),1 + rand.nextInt(3), Product.generateID(), MedicineType.values()[rand.nextInt(MedicineType.values().length)]);
    }
    
}
