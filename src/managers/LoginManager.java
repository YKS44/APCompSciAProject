package managers;

import java.util.HashMap;

import administration.Account;
import administration.Customer;
import administration.Employee;
import administration.Employee.EmployeeLevel;

public class LoginManager {
    private static LoginManager instance = null;

    private HashMap<String, Account> loginMap = new HashMap<>(); //TODO make this into reading file later

    private LoginManager(){
        loginMap.put("customer", new Customer(100.0));
        loginMap.put("employee", new Employee(EmployeeLevel.BOSS));
    }

    private Account currentlyLogin;

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
