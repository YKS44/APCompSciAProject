package ui.pages;

import java.util.Random;

import administration.Customer;
import administration.Employee;
import administration.Account.AccountLevel;
import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;
import products.Dairy;
import products.Drink;
import products.Fish;
import products.Meat;
import products.Medicine;
import products.Movable;
import products.Product;
import products.Snack;
import products.Vegetable;

/**
 * The store page containing the aisles for different types of food. 
 */
public class StorePage extends AbstractPage{

    private static StorePage instance = null;

    private final UIManager uim = UIManager.getInstance();
    
    public Product[] section = {new Dairy(0,0,getHeading(), 0, null), new Meat(0, 0, getMessage1(), 0, null), new Vegetable(0, 0, getHeading(), 0, null), new Drink(0, 0, getHeading(), 0, null), new Snack(0, 0, getHeading(), 0, null), new Medicine(0, 0, getHeading(), null), new Fish(0, 0, getHeading(), 0, null)};
    private final int longestSectionName;
    public Product[][] aisle = new Product[section.length][5]; //dairy, meat, vegetable, drinks, snack, medicine, fish

    private StorePage(){
        int tempMax = 0;
        for(Product product : section){
            if(product.getClass().getName().length()-8 > tempMax){
                tempMax = product.getClass().getName().length()-8;
            }
        }
        longestSectionName = tempMax;

        setUpCommand();
        fillAisle();
    }

    /**
     * Fills the aisles with random products but with the same type of product for each row.
     */
    private void fillAisle(){
        Random rand = new Random();

        for(int i = 0; i < aisle.length; i++){
            for(int j = 0; j < aisle[i].length; j++){
                if(rand.nextDouble() < 0.6){
                    aisle[i][j] = section[i].generateProduct();
                }
            }
        }
    }

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

    private void setProductAt(int[] loc, Product product){
        aisle[loc[0]][loc[1]] = product;
    }

    public void openStorePage(){ 
        uim.printPage(getInstance());
    }

    public void closeStorePage(){
        LoginScreen.getInstance().startHomeScreen();
    }

    /**
     * Converts a string in the format of #,# to an int array containing the coordinates of the aisles.
     * 
     * @param location The string location to convert into a location.
     * @return An int array with the location in the aisle.
     */
    private int[] convertToLocation(String location) {
        String[] numbersArray = location.split(",");
        int[] intArray = new int[numbersArray.length];

        for (int i = 0; i < numbersArray.length; i++) {
            intArray[i] = Integer.parseInt(numbersArray[i]);
        }

        return intArray;
    }

    private Product getProductAt(int[] loc){
        try{
            Product product = aisle[loc[0]][loc[1]];
            return product;
        }catch(IndexOutOfBoundsException e){
            this.setMessage1(uim.getColoredText("red", "Please input a valid location"));
            return null;
        }
    }

    public static StorePage getInstance(){
        if(instance == null){
            instance = new StorePage();
        }
        return instance;
    }

    private String filterProductName(String product){
        return product.substring(9,product.length());
    }

    @Override
    public void update(){
        if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Customer){
            Customer customer = (Customer) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Money: $%.2f"),"Store Page", customer.getMoneyLeft()));
        }else if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Employee){
            Employee employee = (Employee) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Level: %s"),"Store Page", employee.getAccountLevel()));
        }
    }

    @Override
    public void printPage() {
        String col = " ".repeat(longestSectionName);
        for(int i = 0; i < aisle[0].length; i++){
            col += i;
        }
        System.out.println(col);
        for(int i = 0; i < aisle.length; i++){
            System.out.printf("%-"+longestSectionName+"s", section[i].getClass().getName().substring(9,section[i].getClass().getName().length()));
            for(int j = 0; j < aisle[i].length; j++){
                if(aisle[i][j] != null){
                    System.out.print("■");
                }else{
                    System.out.print("☐");
                }
            }
            System.out.print(" " + i);
            System.out.println();
        }
        this.setMessage2(uim.getColoredText("yellow", "Type 'help' for help"));
    }

    @Override
    protected void setUpCommand() {
        CommandManager cmd = CommandManager.getInstance();

        cmd.addCommand(getClass().getName(), "exit", (arg) -> {
            closeStorePage();
        });

        cmd.addCommand(getClass().getName(), "help", (arg) -> {
            this.setMessage1(
                "exit                - Exits store page\n"+
                "info #,#            - Gives info about that product\n"+
                "add {name} #,#      - Adds that type of product at the specified location\n"+
                "move #,#, #,#       - Moves the product from loc1 to loc2\n"+
                "buy #,#             - Buys product at that location\n"+
                "cp #,# #            - Changes the price of the product\n"+
                "sum {quantity||sum} - Gets the total sum or quantity of the entire store"
                );
        });

        cmd.addCommand(getClass().getName(), "info", (arg) -> {
            if(arg.length == 1){
                try{
                    int[] loc = convertToLocation(arg[0]);
                    Product selectedProduct = getProductAt(loc);
                    if(selectedProduct != null){
                        this.setMessage1(selectedProduct.toString());
                    }else{
                        this.setMessage1(uim.getColoredText("red", "That location is empty"));
                    }
                }catch(Exception e){
                    this.setMessage1(uim.getColoredText("red", "Please input a correct location"));
                }
            }else{
                this.setMessage1(uim.getColoredText("red", "Please input a correct location"));
            }
        });

        cmd.addCommand(getClass().getName(), "add", (arg) -> {
            if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel() == AccountLevel.BOSS){
                if(arg.length == 2){
                    try{
                        int[] loc = convertToLocation(arg[1]);
                        Product product = null;
    
                        for(Product prod : section){
                            if(filterProductName(prod.getClass().getName()).equalsIgnoreCase(arg[0])){
                                product = prod;
                            }
                        }
                        if(product == null){
                            this.setMessage1(uim.getColoredText("red", "Unknown product"));
                            return;
                        }

                        if(!filterProductName(section[loc[0]].getClass().getName()).equalsIgnoreCase(arg[0])){
                            this.setMessage1(uim.getColoredText("red", "You can only move products to aisles of the same type."));
                            return;
                        }
                        
                        if(StoragePage.getInstance().getStorage().size() > 0){
                            product = product.generateProduct();
                            product.setQuantity(StoragePage.getInstance().getTicket());
                        }else{
                            this.setMessage1(uim.getColoredText("red", "You have not enough minerals."));
                            return;
                        }


                        if(addProduct(loc, product)){
                            this.setMessage1(uim.getColoredText("green", "Successfully added " + arg[0] + " at " + arg[1]));
                        }else{
                            this.setMessage1(uim.getColoredText("red", "Please select an empty location"));
                        }
                    }catch(Exception e){
                        this.setMessage1(uim.getColoredText("red", "Please input a correct location"));
                    }
                }else{
                    this.setMessage1(uim.getColoredText("red", "Syntax: {product_name} #,#"));
                }
            }else{
                this.setMessage1(uim.getColoredText("red", "You must be a boss to use this command!"));
            }
        });

        cmd.addCommand(getClass().getName(), "move", (arg) -> {
            if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel().level >= AccountLevel.WORKER.level){
                if(arg.length == 2){
                    if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel().level >= 2){
                        try{
                            int[] from = convertToLocation(arg[0]);
                            int[] to   = convertToLocation(arg[1]);
        
                            if(getProductAt(from) != null){
                                if(getProductAt(from) instanceof Movable){
                                    if(filterProductName(getProductAt(from).getClass().getName()).equals(filterProductName(section[to[0]].getClass().getName()))){
                                        int amtLost = ((Movable)getProductAt(from)).move();
                                        if(addProduct(to, getProductAt(from))){
                                            removeProduct(from);
                                            if(amtLost > 0){
                                                this.setMessage2(uim.getColoredText("yellow", amtLost + " quantity was lost during the relocation!"));
                                            }
                                            this.setMessage1(uim.getColoredText("green", "Successfully moved the product."));
                                        }else{
                                            this.setMessage1(uim.getColoredText("red", "You can only move this to an empty slot"));
                                        }
                                    }else{
                                        this.setMessage1(uim.getColoredText("red", "You can only move products to aisles of the same type."));
                                    }
                                }else{
                                    this.setMessage1(uim.getColoredText("red", "This product is not movable!"));
                                }
                            }else{
                                this.setMessage1(uim.getColoredText("red", "That slot is empty"));
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            this.setMessage1(uim.getColoredText("red", "Please input the correct argument"));
                        }
                    }else{
                        this.setMessage1(uim.getColoredText("red", "You must be a worker or a higher status to run this command"));
                    }
                }else{
                    this.setMessage1(uim.getColoredText("red", "Syntax: move #,# #,#"));
                }
            }else{
                this.setMessage1(uim.getColoredText("red", "You must be at least a worker to use this commmand!"));
            }
        });

        cmd.addCommand(getClass().getName(), "buy", (arg) -> {
            if(arg.length == 1){
                if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel() == AccountLevel.CUSTOMER){
                    int[] loc = convertToLocation(arg[0]);

                    if(getProductAt(loc) == null){
                        this.setMessage1(uim.getColoredText("red", "Please choose a location with a product"));
                        return;
                    }

                    Product selectedProduct = getProductAt(loc);
    
                    if(selectedProduct.getPrice() < ((Customer)LoginManager.getInstance().getCurrentlyLoggedIn()).getMoneyLeft()){
                        removeProduct(loc);
                        this.setMessage1("Item bought!");
                        ((Customer)LoginManager.getInstance().getCurrentlyLoggedIn()).reduceMoney(selectedProduct.getPrice());
                    }else{
                        this.setMessage1(uim.getColoredText("red", "You have not enough minerals"));
                    }
                }else{
                    this.setMessage1(uim.getColoredText("red", "Only customers can use this command."));
                }
            }
            else{
                this.setMessage1(uim.getColoredText("red", "Please select a valid location"));
            }
        });
        
        cmd.addCommand(getClass().getName(), "cp", (arg) -> {
            if(arg.length == 2){
                if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel() == AccountLevel.BOSS){
                    int[] loc = convertToLocation(arg[0]);
                    Product product = getProductAt(loc);
                    double price = Double.parseDouble(arg[1]);

                    product.setPrice(price);
                    setProductAt(loc, product);
                    this.setMessage1("Changed price to $" + price);
                }else{
                    this.setMessage1(uim.getColoredText("red", "You do not have access to this command."));
                }
            }else{
                this.setMessage1(uim.getColoredText("red","Syntax: cp #,# #"));
            }
        });

        cmd.addCommand(getClass().getName(), "sum", (arg) -> {
            if(arg.length == 1){
                if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel().level >= AccountLevel.INTERN.level){
                    if(arg[0].equals("quantity")){
                        int quantity = 0;
    
                        for(Product[] row : aisle){
                            for(Product product : row){
                                if(product != null){
                                    quantity += product.getQuantity();
                                }
                            }
                        }
    
                        this.setMessage1("There are a total of " + quantity + " total quantity in the store.");
                    }else if(arg[0].equals("price")){
                        double price = 0.0;
    
                        for(Product[] row : aisle){
                            for(Product product : row){
                                if(product != null){
                                    price += product.getPrice() * product.getQuantity();
                                }
                            }
                        }
                        this.setMessage1("The total price of all products in the store is $" + price);
                    }else{
                        this.setMessage1(uim.getColoredText("red", "Syntax: sum {quantity || price}"));
                    }
                }else{
                    this.setMessage1(uim.getColoredText("red", "You must be at least an intern to use this command."));
                }
            }else{
                this.setMessage1(uim.getColoredText("red", "Syntax: sum {quantity || price}"));
            }
        });
    }
}
