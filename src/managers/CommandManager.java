package managers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import ui.pages.AbstractPage;

public class CommandManager{
    private static CommandManager instance;

    private HashMap<String,HashMap<String,Consumer<String[]>>> commandMap;
    private final Scanner scan;

    private CommandManager(){
        commandMap = new HashMap<>();
        scan = new Scanner(System.in);
    }

    public void addCommand(String key, String name, Consumer<String[]> action){
        if(commandMap.containsKey(key)){
            commandMap.get(key).put(name,action);
        }else{
            HashMap<String,Consumer<String[]>> newMap = new HashMap<>();
            newMap.put(name,action);
            commandMap.put(key, newMap);
        }
    }

    public void handleCommand(AbstractPage page){
        String input = scan.nextLine();

        String[] splitInput = input.split(" ");

        if(commandMap.containsKey(page.getClass().getName()) && commandMap.get(page.getClass().getName()).containsKey(splitInput[0])){
            if(splitInput.length >= 2){
                commandMap.get(page.getClass().getName()).get(splitInput[0]).accept(Arrays.copyOfRange(splitInput,1,splitInput.length));
            }else{
                commandMap.get(page.getClass().getName()).get(splitInput[0]).accept(new String[] {splitInput[0]});
            }
        }else{
            page.setMessage1(UIManager.getInstance().getColoredText("red", "Unknown Command"));
        }
        UIManager.getInstance().printPage(page);
    }

    public static CommandManager getInstance(){
        if(instance == null)
        {
            instance = new CommandManager();
        }

        return instance;
    }
}