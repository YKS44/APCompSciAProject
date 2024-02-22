import products.Product;

public class Store {
    
    public static Product[][] isles = new Product[7][5]; //dairy, meat, vegetable, drinks, snack, medicine, fish

    public static Product getProductAt(int row, int col){
        return isles[row][col];
    }
}
