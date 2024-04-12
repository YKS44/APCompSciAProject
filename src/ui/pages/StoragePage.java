package ui.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import administration.Customer;
import administration.Employee;
import administration.Account.AccountLevel;
import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;

/**
 * Contains a list of "tickets" that the store owner can use to import more items.
 */
public class StoragePage extends AbstractPage{

    private static StoragePage instance = null;
    private final UIManager uim = UIManager.getInstance();

    private List<Integer> storage;
    
    private StoragePage(){
        storage = new ArrayList<>();
        setUpCommand();
    }

    @Override
    public void update(){
        if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Customer){
            Customer customer = (Customer) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Money: $%.2f"),"Storage Page", customer.getMoneyLeft()));
        }else if(LoginManager.getInstance().getCurrentlyLoggedIn() instanceof Employee){
            Employee employee = (Employee) LoginManager.getInstance().getCurrentlyLoggedIn();
            this.setHeading(String.format(uim.getColoredText("green", "%-31s") + uim.getColoredText("cyan", "Level: %s"),"Storage Page", employee.getAccountLevel()));
        }
        this.setMessage2(uim.getColoredText("yellow", "Type 'help' for help"));
    }

    /**
     * Opens the storage page.
     */
    public void openStoragePage(){
        uim.printPage(getInstance());
    }

    /**
     * Closes the storage page.
     */
    public void closeStoragePage(){
        LoginScreen.getInstance().startHomeScreen();
    }

    /**
     * Returns the entire list of food tickets.
     * 
     * @return
     */
    public List<Integer> getStorage(){
        return storage;
    }

    /**
     * Returns the number value of the last ticket in the storage.
     * 
     * @return Number value of the last ticket in the storage.
     */
    public int getTicket(){
        return storage.remove(storage.size()-1);
    }

    @Override
    public void printPage() {
        System.out.print("Food Tickets: ");
        Iterator<Integer> iterator = storage.iterator();

        while(iterator.hasNext()){
            Integer a = iterator.next();
            System.out.print("|"+a.intValue()+"| ");
        }
        System.out.println();
    }

    @Override
    protected void setUpCommand() {
        CommandManager cmd = CommandManager.getInstance();
        Random rand = new Random();

        cmd.addCommand(getClass().getName(), "help", (arg) -> {
            this.setMessage1(
                "exit  - Exits the storage page\n"+
                "order - Orders a food ticket with a random quantity"
            );
        });

        cmd.addCommand(getClass().getName(), "exit", (arg) -> {
            closeStoragePage();
        });

        cmd.addCommand(getClass().getName(), "order", (arg) -> {
            if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel() == AccountLevel.BOSS){
                storage.add(rand.nextInt(10)+1);
            }else{
                this.setMessage1(uim.getColoredText("red", "Only the boss can use this command."));
            }
        });
    }
    
    public static StoragePage getInstance(){
        if(instance == null){
            instance = new StoragePage();
        }
        return instance;
    }
}
