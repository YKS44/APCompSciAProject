import ui.pages.LoginScreen;

public class Main {
    public static void main(String[] args){
        LoginScreen.getInstance().initializeStore();
    }

    /*
     * List of things to do:
     * 
     * - Make some abilities specific to account levels
     * - Add a storage page so that you can bring stuff from the storage to the store
     * - Make customer abilities (they can select items to put in their cart and order it)
     * - Make auto discount system
     * - Make an order page where you can order products for the storage
     * - Make it so that the boss can change the price of products
     * - Make it so that there is a chance for some quantity of a product to be lost when moving (different for all products)
     * - Make it so that you cannot move products to aisles that it doesnt belong
     * - Add username to accounts?
     * - Make it so that you can you can get the total quantity/price of the whole store
     * 
     *  Done:
     * - Automatically fill the store at the beginning of the program
     * 
     */
}
