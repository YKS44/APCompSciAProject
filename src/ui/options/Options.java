package ui.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holder for multiple {@link Option Options}. 
 */
public class Options {
    private ArrayList<Option> options;
    private String title;

    /**
     * Creates a new instance of Options. Call {@link #buildOptions(ArrayList, String)} or {@link #buildOptions(Option[], String)} to make a new Options outside of this class.
     * 
     * @param options The list of options.
     * @param title The title of the options.
     */
	private Options(ArrayList<Option> options, String title){
		this.options = options;
        this.title = title;
    }

    /**
     * Builds a new Options with an array of options and a title.
     * 
     * @param options The array of options.
     * @param name The title of the Options.
     * @return A new Options instance with the given parameters.
     */
    public static Options buildOptions(Option[] options, String name){
        return new Options(new ArrayList<>(Arrays.asList(options)), name);
    }

    /**
     * Builds a new Options with an ArrayList of options and a title.
     * 
     * @param options The ArrayList of options.
     * @param name The title of the Options.
     * @return A new Options instance with the given parameters.
     */
    public static Options buildOptions(ArrayList<Option> options, String name){
        return new Options(options, name);
    }

    /**
     * Returns the ArrayList of options.
     * 
     * @return The ArrayList of options
     */
    public List<Option> getOptions(){
	    return options;
    }

    /**
     * Returns the title of the Options.
     * 
     * @return The title of the Options
     */
    public String getTitle(){
        return title;
    }

    /**
     * Prints the options ordered with numbers. 
     */
    public void printOptions(){
        for(int i = 0; i < this.getOptions().size(); i++){
            Option option = this.getOptions().get(i);
            String title = option.getTitle();

            if(option.getIsUnlocked() == true){
                System.out.println((i+1) + ". " + title);
            }
        }
    }
}
