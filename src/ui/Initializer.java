package ui;

import java.util.Scanner;

import managers.LoginManager;
import managers.UIManager;
import ui.options.OptionPath;

public class Initializer {
    private static Initializer instance = null;

    public void initializeStore() throws InterruptedException{
        Scanner scan = new Scanner(System.in);
        UIManager uim = UIManager.getInstance();
        uim.clearScreen();

        boolean loginSuccessful = false;

        while(!loginSuccessful){
            System.out.print("Enter password: ");
            String input = scan.nextLine();
    
            if(LoginManager.getInstance().login(input)){
                uim.clearScreen();
                loginSuccessful = true;
                uim.printInColor("green", "Login successful!");
                System.out.println("\nHomepage loading...");
                Thread.sleep(2000);
                uim.clearScreen();
                uim.sendAndReceive(OptionPath.mainPage);
            }else{
                uim.clearScreen();
                uim.printInColor("red", "Login unsuccessful");
            }
        }
        scan.close();
    }

    public static Initializer getInstance(){
        if(instance == null){
            instance = new Initializer();
        }
        return instance;
    }
}
