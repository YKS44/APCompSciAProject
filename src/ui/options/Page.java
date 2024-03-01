package ui.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import administration.Customer;
import administration.Employee;
import managers.LoginManager;
import managers.UIManager;

public class Page {
    private ArrayList<Option> options;
    private String title;

    private static final UIManager uim = UIManager.getInstance();

	private Page(ArrayList<Option> options, String title){
		this.options = options;
        this.title = title;
    }

    public static Page buildPage(Option[] options, String name){
        return new Page(new ArrayList<>(Arrays.asList(options)), name);
    }

    public static Page buildPage(ArrayList<Option> options, String name){
        return new Page(options, name);
    }

    public List<Option> getOptions(){
	    return options;
    }

    public String getTitle(){
        return title;
    }

    public void printPage(){
        if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Customer){
            Customer customer = (Customer) LoginManager.getInstance().getCurrentlyLoggedIn();
            System.out.printf(uim.getColoredText("green", "%-31s ")+ uim.getColoredText("cyan", "Money: $%.2f\n\n"),this.getTitle(), customer.getMoneyLeft());
        }else{
            Employee employee = (Employee) LoginManager.getInstance().getCurrentlyLoggedIn();
            System.out.printf(uim.getColoredText("green", "%-31s ")+ uim.getColoredText("cyan", "Level: %s\n\n"),this.getTitle(), employee.getAccountLevel());
        }

        for(int i = 0; i < this.getOptions().size(); i++){
            Option option = this.getOptions().get(i);
            String title = option.getTitle();

            if(option.getIsUnlocked() == true){
                System.out.println((i+1) + ". " + title);
            }else{
                System.out.println((i+1) + ". " + uim.getColoredText("red", "[NOT ACCESSIBLE]"));
            }
            
        }

        System.out.println("");

        System.out.println(uim.getMessage1());
        System.out.println(uim.getMessage2());

        uim.setMessage1("");
    }
}
