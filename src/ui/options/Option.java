package ui.options;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * An option class that can have a name, action, and other attributes such as locking options.
 */
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

    /**
     * Returns the title of the option.
     * 
     * @return The title of the option
     */
    public String getTitle(){
    	return title;
    }

    /**
     * Returns the action of the option.
     * 
     * @return The action of the option.
     */
    public SimpleAction getAction(){
	    return action;   
    }

    /**
     * Checks whether or not the option is locked.
     * 
     * @return True is the option is locked, false otherwise.
     */
    public boolean getIsUnlocked(){
        return  isUnlockedSupplier.getAsBoolean();
    }

    /**
     * Returns the description of the option. If the option is locked, it will return the locked description.
     * 
     * @return Description of the option.
     */
    public String getDescription(){
        return isUnlockedSupplier.getAsBoolean() ? description.get() : lockDescription.get();
    }
}