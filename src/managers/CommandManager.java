package managers;

import java.util.HashMap;
import java.util.function.Consumer;

import ui.options.Action;
import ui.options.Page;

public class CommandManager{
    private static CommandManager instance;

    private HashMap<String,Action> commandMap;
    // private HashMap<String,Consumer<String[]>> commandMap;

    private CommandManager(){
        commandMap = new HashMap<>();
    }

    public void addCommand(String name, Action action){
        commandMap.put(name,action);
    }

    public void invokeCommand(String name, Page page){
        //If the UIManager queries a command that is registered in the map, then execute it.
        if(commandMap.containsKey(name)){
            commandMap.get(name).execute();
        }
        
        //If it is not found, then it means the user has inputted something weird, so throw an error.
        else
        {
            UIManager.getInstance().setMessage1(UIManager.getInstance().getColoredText("red", "Incorrect Input"));
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