package managers;

import java.util.HashMap;

import administration.Account;
import administration.Account.AccountLevel;
import administration.Customer;
import administration.Employee;

/**
 * Handles accounts. Contains password and account level for all accounts. 
 */
public class LoginManager {
    private static LoginManager instance = null;

    private HashMap<String, Account> loginMap = new HashMap<>(); 
    private Account currentlyLogin;

    private LoginManager(){
        loginMap.put("customer", new Customer(100.0));
        loginMap.put("boss", new Employee(AccountLevel.BOSS));
        loginMap.put("worker", new Employee(AccountLevel.WORKER));
        loginMap.put("intern", new Employee(AccountLevel.INTERN));
    }

    /**
     * Returns the currently logged in account.
     * 
     * @return The currently logged in account.
     */
    public Account getCurrentlyLoggedIn(){
        return currentlyLogin;
    }

    /**
     * Attempts to login with a given password
     * 
     * @return True if login was successful. False otherwise.
     */
    public boolean login(String password){
        Account acct = loginMap.get(password);

        if(acct == null){
            return false;
        }
        else{
            currentlyLogin = acct;
            return true;
        }
    }

    public static LoginManager getInstance(){
        if(instance == null){
            instance = new LoginManager();
        }
        return instance;
    }
}
