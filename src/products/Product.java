package products;

public abstract class Product {
    protected int[] location = new int[2];
    protected double price;
    protected int quantity;

    public Product(int[] location, double price, int quantity){
        this.location = location;
        this.price = price;
        this.quantity = quantity;
    }
    
    public double getPrice() {return price;}
    public void setPrice(double price){this.price = price;}    

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public void purchase(){}
    public abstract double getDiscount();
}
