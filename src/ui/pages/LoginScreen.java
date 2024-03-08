package ui.pages;

import java.util.Scanner;

import managers.LoginManager;
import managers.UIManager;

public class LoginScreen {
    private static LoginScreen instance = null;

    private boolean endProgram = false;
    private boolean goBackToLoginPage = false;

    private final UIManager uim = UIManager.getInstance();

    public void initializeStore(){
        loginPage();
    }

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
                    Thread.sleep(1000);
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

    public void endProgram(){
        endProgram = true;
    }

    public void goBackToLoginPage(){
        goBackToLoginPage = true;
        endProgram = true;
    }

    public void startHomeScreen(){
        endProgram = false;
        while(!endProgram){
            uim.printPage(MainPage.getInstance());
        }

        if(goBackToLoginPage){
            goBackToLoginPage = false;
            loginPage();
        }
    }

    public static LoginScreen getInstance(){
        if(instance == null){
            instance = new LoginScreen();
        }
        return instance;
    }
}
