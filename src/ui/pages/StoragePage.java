package ui.pages;

import java.util.ArrayList;
import java.util.List;


import administration.Customer;
import administration.Employee;
import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;
import products.Meat;
import products.Product;

public class StoragePage extends AbstractPage{

    private static StoragePage instance = null;
    private final UIManager uim = UIManager.getInstance();

    private List<Product> storage;
    
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

    public void openStoragePage(){
        uim.printPage(getInstance());
    }

    public void closeStoragePage(){
        LoginScreen.getInstance().startHomeScreen();
    }

    @Override
    public void printPage() {
        System.out.print("Products: ");
        for(Product product : storage){
            System.out.print("■");
        }
        System.out.println();
    }

    @Override
    protected void setUpCommand() {
        CommandManager cmd = CommandManager.getInstance();

        cmd.addCommand(getClass().getName(), "add", (arg) -> {
            storage.add(new Meat(new int[]{0,0}, 100.0, 3, 0.5, 0.1, "Pork"));
        });

        cmd.addCommand(getClass().getName(), "remove", (arg) -> {
            storage.remove(0);
        });

        cmd.addCommand(getClass().getName(), "exit", (arg) -> {
            closeStoragePage();
        });
    }
    
    public static StoragePage getInstance(){
        if(instance == null){
            instance = new StoragePage();
        }
        return instance;
    }
}
