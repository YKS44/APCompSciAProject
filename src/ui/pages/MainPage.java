package ui.pages;


import java.util.ArrayList;
import java.util.List;

import administration.Customer;
import administration.Employee;
import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;
import ui.options.MainPageOptionPath;
import ui.options.Option;
import ui.options.Page;

public class MainPage extends AbstractPage{
    private static MainPage instance = null;  
    private Page currentPage;
    private List<Page> prev;
    
    private UIManager uim = UIManager.getInstance();

    private MainPage(){
        currentPage = MainPageOptionPath.mainPage;
        prev = new ArrayList<>();

        setUpCommand();
    }

    @Override
    public void update(){
        if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Customer){
            Customer customer = (Customer) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Money: $%.2f"),"Main Page", customer.getMoneyLeft()));
        }else if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Employee){
            Employee employee = (Employee) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Level: %s"),"Main Page", employee.getAccountLevel()));
        }
        this.setMessage2(uim.getColoredText("yellow", "Type 'help' for help"));
    }

    @Override
    public void printPage() {
        currentPage.printPage();
    }

    @Override
    protected void setUpCommand() {
        CommandManager cmd = CommandManager.getInstance();

        cmd.addCommand(getClass().getName(),"h", (arg) -> {
            currentPage = MainPageOptionPath.mainPage;
        });

        cmd.addCommand(getClass().getName(),"b", (arg) -> {
            if(!prev.isEmpty()){
                Page back = prev.remove(prev.size()-1);
                currentPage = back;
            }else{
                currentPage = MainPageOptionPath.mainPage;
            }
        });

        cmd.addCommand(getClass().getName(),"c", (arg) -> {
            if(arg.length == 1 && isInteger(arg[0])){
                int selectedOption = Integer.parseInt(arg[0]) - 1;
                
                try{
                    Option selected = currentPage.getOptions().get(selectedOption);

                    if(selected.getIsUnlocked() == true){
                        prev.add(currentPage);
                        selected.getAction().execute();
                    }else{
                        setMessage1(uim.getColoredText("red", "This option is not accessible."));
                    }
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                    this.setMessage1(uim.getColoredText("red", "Please input a correct number"));
                }
                
            }else{
                this.setMessage1(uim.getColoredText("red", "Please input a correct number"));
            }
        });

        cmd.addCommand(getClass().getName(),"i", (arg) -> {
            if(arg.length == 1 && isInteger(arg[0])){
                int selectedOption = Integer.parseInt(arg[0]) - 1;

                this.setMessage1(getDescription(selectedOption));
            }else{
                this.setMessage1(uim.getColoredText("red", "Please input a correct number"));
            }
        });

        cmd.addCommand(getClass().getName(), "help", (arg) -> {
            if(arg.length == 1){
                this.setMessage1("h     - Goes back to home screen\n"+
                                 "b     - Goes back a page\n"+
                                 "c [#] - Executes that option\n"+
                                 "i [#] - Gives more information about that option");
            }
        });
    }

    private String getDescription(int idx){
        if(idx < 0 || idx > currentPage.getOptions().size()){
            return uim.getColoredText("red", "Please type a correct argument.");
        }else{
            return currentPage.getOptions().get(idx).getDescription();
        }
    }

    private boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
        
    public static MainPage getInstance(){
        if(instance == null){
            instance = new MainPage();
        }
        return instance;
    }
}
