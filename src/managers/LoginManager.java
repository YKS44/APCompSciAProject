package managers;

import administration.Account;

public class LoginManager {
    private static LoginManager instance = null;

    private Account currentlyLogin;

    public Account getCurrentlyLoggedIn(){
        return currentlyLogin;
    }

    public static LoginManager getInstance(){
        if(instance == null){
            instance = new LoginManager();
        }
        return instance;
    }
}
