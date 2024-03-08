package ui.pages;
import java.util.Scanner;

import administration.Customer;
import administration.Employee;
import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;
import products.Meat;
import products.Movable;
import products.Product;

public class Store extends AbstractPage{

    private static Store instance = null;

    private final UIManager uim = UIManager.getInstance();
    
    public Product[][] aisle = new Product[7][5]; //dairy, meat, vegetable, drinks, snack, medicine, fish
    private String[] section = {"Dairy", "Meat", "Vegetable ", "Drinks", "Snack", "Medicine", "Fish"};
    private final int longestSectionName;

    private boolean endStorePage = false;

    private String message = "";

    private Store(){
        int tempMax = 0;
        for(String str : section){
            if(str.length() > tempMax){
                tempMax = str.length();
            }
        }
        longestSectionName = tempMax;

        setUpCommand();

        aisle[0][0] = new Meat(new int[]{0,0}, 100.0, 3, 0.5, 0.1, "Pork");
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


    public void closeStorePage(){
        endStorePage = true;
        LoginScreen.getInstance().startHomeScreen();
    }

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
            this.setMessage1(uim.getColoredText("red", "Please input a correct location"));
            return null;
        }
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }

    @Override
    public void update(){
        if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Customer){
            Customer customer = (Customer) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Money: $%.2f"),"Main Page", customer.getMoneyLeft()));
        }else if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Employee){
            Employee employee = (Employee) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Level: %s"),"Main Page", employee.getAccountLevel()));
        }
    }

    @Override
    public void printPage() {
        for(int i = 0; i < aisle.length; i++){
            System.out.printf("%-"+longestSectionName+"s", section[i]);
            for(int j = 0; j < aisle[i].length; j++){
                if(aisle[i][j] != null){
                    System.out.print("■");
                }else{
                    System.out.print("☐");
                }
            }
            System.out.println();
        }
    }

    @Override
    protected void setUpCommand() {
        CommandManager cmd = CommandManager.getInstance();

        cmd.addCommand(getClass().getName(), "exit", (arg) -> {
            closeStorePage();
        });

        cmd.addCommand(getClass().getName(), "info", (arg) -> {
            if(arg.length == 1){
                try{
                    int[] loc = convertToLocation(arg[0]);
                    this.setMessage1(getProductAt(loc).toString());

                }catch(Exception e){
                    this.setMessage1(uim.getColoredText("red", "Please input a correct location"));
                    uim.printPage(getInstance());
                }
            }
        });
    }
}
