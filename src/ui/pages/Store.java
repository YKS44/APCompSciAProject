package ui.pages;
import java.util.Scanner;

import managers.UIManager;
import products.Meat;
import products.Product;

public class Store {

    private static Store instance = null;

    private final UIManager uim = UIManager.getInstance();
    
    public Product[][] aisle = new Product[7][5]; //dairy, meat, vegetable, drinks, snack, medicine, fish
    private String[] section = {"Dairy", "Meat", "Vegetable ", "Drinks", "Snack", "Medicine", "Fish"};
    private final int longestSectionName;

    private boolean endStorePage = false;

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

            System.out.print("\nEnter a command: ");
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("exit")){
                closeStorePage();
            }
        }

        HomeScreen.getInstance().startHomeScreen();
    }

    public void closeStorePage(){
        endStorePage = true;
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
}
