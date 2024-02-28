import products.Product;

public class Store {

    private static Store instance = null;
    
    public Product[][] aisle = new Product[7][5]; //dairy, meat, vegetable, drinks, snack, medicine, fish

    public Product getProductAt(int row, int col){
        return aisle[row][col];
    }

    /**
     * Removes a product at the given location
     * 
     * @return True if the specified location had a product, false otherwise
     */
    public boolean removeProduct(int[] loc){
        if(aisle[loc[0]][loc[1]] == null){
            return false;
        }else{
            aisle[loc[0]][loc[1]] = null;
            return true;
        }
    }

    /**
     * Adds a product at the given location
     * 
     * @return True if the specified location was empty, false otherwise
     */
    public boolean addProduct(int[] loc, Product product){
        if(aisle[loc[0]][loc[1]] != null){
            return false;
        }
        else{
            aisle[loc[0]][loc[1]] = product;
            return true;
        }
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
}
