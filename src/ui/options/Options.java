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

	private Options(ArrayList<Option> options, String title){
		this.options = options;
        this.title = title;
    }

    public static Options buildPage(Option[] options, String name){
        return new Options(new ArrayList<>(Arrays.asList(options)), name);
    }

    public static Options buildPage(ArrayList<Option> options, String name){
        return new Options(options, name);
    }

    public List<Option> getOptions(){
	    return options;
    }

    public String getTitle(){
        return title;
    }

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
