package managers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

import ui.options.Page;

public class CommandManager{
    private static CommandManager instance;

    private HashMap<String,Predicate<String[]>> commandMap;
    private final Scanner scan;

    private CommandManager(){
        commandMap = new HashMap<>();
        scan = new Scanner(System.in);
    }

    public void addCommand(String name, Predicate<String[]> action){
        commandMap.put(name,action);
    }


    /**
     * Handles command inputs from the user.
     * 
     * @return True if the command was executed successfully. False otherwise.
     */
    public void handleCommandInputs(){
        String input = scan.nextLine();


    }

    public void invokeCommand(String input, Page page){
        String[] splitInput = input.split(" ");
        if(commandMap.containsKey(splitInput[0])){
            if(splitInput.length >= 2){
                commandMap.get(splitInput[0]).test(Arrays.copyOfRange(splitInput,1,splitInput.length));
            }else{
                commandMap.get(splitInput[0]).test(new String[] {splitInput[0]});
            }
        }else{
            UIManager.getInstance().setMessage1(UIManager.getInstance().getColoredText("red", "Incorrect Command"));
            UIManager.getInstance().sendAndReceive(page);
        }
    }

    public static CommandManager getInstance(){
        if(instance == null)
        {
            instance = new CommandManager();
        }

        return instance;
    }
}