package products;

import ui.pages.StorePage;

public class Meat extends FoodProduct implements Movable{
    private String meatType;

    public Meat(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String meatType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.meatType = meatType;
    }

    @Override
    public boolean move(int[] location) {
        if(StorePage.getInstance().addProduct(location, this)){
            StorePage.getInstance().removeProduct(this.location);
            this.location = location;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String result = "";

        result += "Product Type: " + "Meat\n";
        result += "Meat Type: " + meatType + "\n";
        result += super.toString();

        return result;
    }
}
