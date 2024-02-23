import administration.Login;
import ui.UIManager;
import ui.options.OptionPath;

public class Main {
    private static Login currentLoggedIn;
    public static void main(String[] args) {
        UIManager.getInstance().sendAndReceive(OptionPath.mainPage);
    }

    public static Login getCurrentLogin(){
        return currentLoggedIn;
    }
}
