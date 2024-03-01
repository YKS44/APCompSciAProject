package products;

import ui.pages.Store;

public class Meat extends FoodProduct implements Movable{
    private String meatType;

    public Meat(int[] location, double price, int quantity, double expirationPercent, double expirationRate, String meatType) {
        super(location, price, quantity, expirationPercent, expirationRate);
        this.meatType = meatType;
    }

    @Override
    public boolean move(int[] location) {
        if(Store.getInstance().addProduct(location, this)){
            Store.getInstance().removeProduct(this.location);
            this.location = location;
            return true;
        }
        return false;
    }
    
}
