package ui.pages;
import java.util.Scanner;

import managers.UIManager;
import products.Meat;
import products.Movable;
import products.Product;

public class Store {

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

    private void printStore(){
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

    public void openStorePage(){ 
        endStorePage = false;
        HomeScreen.getInstance().endProgram();

        Scanner scan = new Scanner(System.in);
        while(!endStorePage){
            uim.clearScreen();
            printStore();

            System.out.println("\n" + message);
            message = "";
            System.out.print("Enter a command: ");
            String input = scan.nextLine();
            String[] splitInput = input.split(" ");

            if(input.equalsIgnoreCase("exit")){
                closeStorePage();
            }else if(splitInput.length == 2){
                int[] loc = convertToLocation(splitInput[1]);   
                message = aisle[loc[0]][loc[1]].getClass().toString();
            }else if(splitInput.length == 3){
                if(splitInput[0].equals("move")){
                    int[] from = convertToLocation(splitInput[1]);
                    int[] to   = convertToLocation(splitInput[2]);

                    if(aisle[from[0]][from[1]] != null){
                        ((Movable)aisle[from[0]][from[1]]).move(to);
                    }else{
                        message = "That's empty";
                    }
                }
            }else{
                message = "Unknown command";
            }
        }
        scan.close();
        HomeScreen.getInstance().startHomeScreen();
    }

    public void closeStorePage(){
        endStorePage = true;
        HomeScreen.getInstance().startHomeScreen();
    }

    public int[] convertToLocation(String location) {
        String[] numbersArray = location.split(",");
        int[] intArray = new int[numbersArray.length];

        for (int i = 0; i < numbersArray.length; i++) {
            intArray[i] = Integer.parseInt(numbersArray[i]);
        }

        return intArray;
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
}
