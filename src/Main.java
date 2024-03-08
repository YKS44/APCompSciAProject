import managers.CommandManager;
import managers.LoginManager;
import managers.UIManager;
import ui.pages.LoginScreen;
import ui.pages.MainPage;
import ui.pages.StorePage;

public class Main {
    public static void main(String[] args){
        CommandManager.getInstance();
        LoginManager.getInstance();
        UIManager.getInstance();
        LoginScreen.getInstance();
        MainPage.getInstance();
        StorePage.getInstance();

        LoginScreen.getInstance().initializeStore();
    }
}
