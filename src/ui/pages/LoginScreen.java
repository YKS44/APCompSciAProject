package ui.pages;

import java.util.Scanner;

import managers.LoginManager;
import managers.UIManager;

/**
 * The login page. First run when the program starts.
 */
public class LoginScreen {
    private static LoginScreen instance = null;

    private final UIManager uim = UIManager.getInstance();

    public void initializeStore(){
        loginPage();
    }

    /**
     * Prints the login page and handles the logins with a password.
     */
    private void loginPage() {
        Scanner scan = new Scanner(System.in);
        boolean loginSuccessful = false;
        uim.clearScreen();

        while(!loginSuccessful){
            System.out.print("Enter password: ");
            String input = scan.nextLine();
    
            if(LoginManager.getInstance().login(input)){
                uim.clearScreen();
                loginSuccessful = true;
                uim.printInColor("green", "Login successful!");
                System.out.println("\nHomepage loading...");
                try{
                    Thread.sleep(500);
                    startHomeScreen();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                uim.clearScreen();
                uim.printInColor("red", "Login unsuccessful");
            }
        }
        scan.close();
    }

    /**
     * Goes back to the login page.
     */
    public void goBackToLoginPage(){
        loginPage();
    }

    /**
     * Starts the {@link ui.pages.MainPage MainPage}.
     */
    public void startHomeScreen(){
        uim.printPage(MainPage.getInstance());
    }

    public static LoginScreen getInstance(){
        if(instance == null){
            instance = new LoginScreen();
        }
        return instance;
    }
}
