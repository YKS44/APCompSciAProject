package products;

import java.util.Random;

/**
 * The base class for all products. Contains informations such as price, quantity, and its product id.
 */
public abstract class Product {
    protected double price;
    protected int quantity;
    private String id;

    public Product(double price, int quantity, String id){
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }
    
    public double getPrice() {return price;}
    public void setPrice(double price){this.price = price;}    

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    
    public String getID(){return id;}

    public void purchase(){}
    public abstract double getDiscount();
    public abstract Product generateProduct();

    /**
     * Generates a random product id.
     * @return A random 6 digit product id containing letters and numbers.
     */
    public static String generateID(){
        Random rand = new Random();

        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        String id = "";

        for(int i = 0; i < 5; i++){
            id += s.charAt(rand.nextInt(s.length()));
        }
        return id;
    }

    @Override
    public String toString(){
        String result = "";

        result += "Price: " + "$" + price + "\n";
        result += "Quantity: " + quantity + "\n";
        result += "Product Id: " + id + "\n";

        return result;
    }
}
