import administration.Account;
import managers.UIManager;
import ui.options.OptionPath;

public class Main {
    public static void main(String[] args) {
        UIManager.getInstance().sendAndReceive(OptionPath.mainPage);
    }
}
