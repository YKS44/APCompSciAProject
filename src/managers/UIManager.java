package managers;

import java.util.HashMap;

import ui.pages.AbstractPage;

public class UIManager{
    private final boolean canClearScreen = true; //for testing purposes

    //https://www.w3schools.blog/ansi-colors-java

    private static UIManager instance;

    private final String RESET;

    private HashMap<String, String> colorMap = new HashMap<>();

    private UIManager(){
        RESET = "\033[0m";

        colorMap.put("black","\033[0;30m");
        colorMap.put("red","\033[0;31m");
        colorMap.put("green","\033[0;32m");
        colorMap.put("yellow","\033[0;33m");
        colorMap.put("blue","\033[0;34m");
        colorMap.put("purple","\033[0;35m");
        colorMap.put("cyan","\033[0;36m");
        colorMap.put("white","\033[0;37m");

    }

    public static UIManager getInstance(){
        if(instance == null)
        {
            instance = new UIManager();
        }
        return instance;
    } 


    /**
     * Prints the page with a heading, body, and two sub messages.
     * 
     * @param page The page to print
     */
    public void printPage(AbstractPage page){
        clearScreen();
        page.update();

        System.out.println(page.getHeading()+"\n\n");
        page.printPage();
        System.out.println();
        System.out.println(page.getMessage1());
        System.out.println(page.getMessage2());

        page.setMessage1("");
        CommandManager.getInstance().handleCommand(page);
    }

    /**
     * Clears the terminal
     */
    public void clearScreen(){
        if(canClearScreen){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    /**
     * Prints a text in the console with the specified color.
     * 
     * @param color The color to print the text in.
     * @param message The message to print
     */
    public void printInColor(String color, String message){
        System.out.println(getColor(color) + message + RESET);
    }

    /**
     * @param color The color to convert the text into.
     * @param text The text to apply the color to.
     * @return The given text with the given color
     */
    public String getColoredText(String color, String text){
        return getColor(color) + text + RESET;
    }
    
    /**
     * 
     * @param color The color code to retrieve.
     * @return The color code of the given color.
     */
    private String getColor(String color){
        return colorMap.get(color);
    }

}
