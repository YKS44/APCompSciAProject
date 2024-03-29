package ui.options;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Option{
    private String title;
    private Supplier<String> description;
    private Supplier<String> lockDescription;
	private SimpleAction action;
    private BooleanSupplier isUnlockedSupplier;

    /**
     * Option with a lock condition
     */
	public Option(String title, SimpleAction action, BooleanSupplier isUnlocked, String lockDescription, String description){
		this.title = title;
		this.action = action;
        this.isUnlockedSupplier = isUnlocked;
        this.lockDescription = ()->lockDescription;
        this.description = ()->description;
    }

    /**
     * Normal option
     */
    public Option(String title, SimpleAction action, String description){
        this.title = title;
        this.action = action;
        this.description = ()->description;
        this.isUnlockedSupplier = ()->true;
        
    }

    /**
     * Bought once option with a lock condition
     */
    public Option(String title, SimpleAction action, BooleanSupplier isUnlocked, Supplier<String> lockDescription, String description)
    {
        this.title = title;
        this.action = action;
        this.isUnlockedSupplier = isUnlocked;
        this.lockDescription = lockDescription;
        this.description = ()->description;
    }

    public String getTitle(){
    	return title;
    }

    public SimpleAction getAction(){
	    return action;   
    }

    public boolean getIsUnlocked(){
        return  isUnlockedSupplier.getAsBoolean();
    }

    public String getDescription(){
        return isUnlockedSupplier.getAsBoolean() ? description.get() : lockDescription.get();
    }
}