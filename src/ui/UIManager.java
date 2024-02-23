package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import ui.options.Option;
import ui.options.OptionPath;
import ui.options.Page;

public class UIManager{
    private final boolean canClearScreen = true; //for testing purposes

    //https://www.w3schools.blog/ansi-colors-java

    private static UIManager instance;

    private final Scanner scanner;

    private final String RESET;

    private HashMap<String, String> colorMap = new HashMap<>();

    
    private Page currentPage;
    private String message1;
    private String message2;

    private UIManager(){
        scanner = new Scanner(System.in);

        RESET = "\033[0m";

        colorMap.put("black","\033[0;30m");
        colorMap.put("red","\033[0;31m");
        colorMap.put("green","\033[0;32m");
        colorMap.put("yellow","\033[0;33m");
        colorMap.put("blue","\033[0;34m");
        colorMap.put("purple","\033[0;35m");
        colorMap.put("cyan","\033[0;36m");
        colorMap.put("white","\033[0;37m");

        message1 = "";
        message2 = "";
        currentPage = null;

        setUpCommand();
    }

    public static UIManager getInstance(){
        if(instance == null)
        {
            instance = new UIManager();
        }
        return instance;
    } 


    public List<Page> prev = new ArrayList<>();

    public void sendAndReceive(Page options){
        //inserts the current page into the previous page list so that the user can trace back their visited pages
        if(currentPage != null && !prev.contains(currentPage) && !prev.isEmpty() && prev.get(prev.size()-1) != currentPage){
            prev.add(currentPage);
        }
        currentPage = options;

        printOptions(options);

        String input = scanner.nextLine();
        
        clearScreen();

        try{
            //Try to parse the input into an integer. If it succeeds, then it means the user is attempting to select an option.
            int idx = Integer.parseInt(input) - 1;
            
            Option selected = options.getOptions().get(idx);

            if(selected.getIsUnlocked() == true){
                prev.add(options);
                selected.getAction().execute();
            }else{
                setMessage1(getColoredText("red", "This option is not accessible."));
                sendAndReceive(options);
            }
            
        }catch(IndexOutOfBoundsException | NumberFormatException e){
            //If it catches an error, then it means one of two things: User attempted to run a command or 
            //they just put in a number out of range of the options.

            //If the input started with 'i' and the amount of words in the string is equal to two,
            //it means the user is trying to use the info command.
            if(input.startsWith("i") && input.split(" ").length == 2){
                String[] cmd = input.split(" ");
                try{
                    int idx = Integer.parseInt(cmd[1]);

                    setMessage1(getDescription(idx - 1));
                }catch(NumberFormatException | IndexOutOfBoundsException e2){
                    setMessage1(getColoredText("red", "Please input a correct number"));
                }
                sendAndReceive(options);
            }else{
                //Else, it is using other commands, such as the 'home' or 'back' command. If the user inputs 
                //gibberish or a command that is not accessible, then the command manager class will handle it.
                CommandManager.getInstance().invokeCommand(input, options);
            }
        }

    }

    private String getDescription(int idx){
        if(idx < 0 || idx > currentPage.getOptions().size()){
            return getColoredText("red", "Please type a correct argument.");
        }else{
            return currentPage.getOptions().get(idx).getDescription();
        }
    }

    public void printOptions(Page options){
        System.out.printf(getColoredText("green", "%-31s ")+ getColoredText("cyan", "Money: $%d\n\n"),options.getTitle());

        for(int i = 0; i < options.getOptions().size(); i++){
            Option option = options.getOptions().get(i);
            String title = option.getTitle();

            if(option.getIsUnlocked() == true){
                System.out.println((i+1) + ". " + title);
            }else{
                System.out.println((i+1) + ". " + getColoredText("red", "[NOT ACCESSIBLE]"));
            }
            
        }

        System.out.println("");

        System.out.println(message1);
        System.out.println(message2);

        message1 = "";
    }

    public void clearScreen(){
        if(canClearScreen){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public void printInColor(String color, String message){
        System.out.println(getColor(color) + message + RESET);
    }

    public String getColoredText(String color, String text){
        return getColor(color) + text + RESET;
    }

    public void setMessage1(String message){
        this.message1 = message;
    }

    public void setMessage2(String message){
        this.message2 = message;
    }

    private String getColor(String color){
        return colorMap.get(color);
    }

    public Page getCurrentPage()
    {
        return currentPage;
    }

    private void setUpCommand(){
        CommandManager cmd = CommandManager.getInstance();

        cmd.addCommand("h", () -> sendAndReceive(OptionPath.mainPage));
        cmd.addCommand("b", () -> {
            if(!prev.isEmpty()){
                Page back = prev.get(prev.size() - 1);
                prev.remove(prev.size() - 1);
                currentPage = null;
                sendAndReceive(back);
            }else{
                sendAndReceive(OptionPath.mainPage);
            }
        });
    }
}
