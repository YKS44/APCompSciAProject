package ui.options;

import managers.UIManager;
import ui.pages.AbstractPage;
import ui.pages.LoginScreen;
import ui.pages.MainPage;
import ui.pages.Store;

public class MainPageOptionPath {
    private static final LoginScreen game = LoginScreen.getInstance();

    private static UIManager uim = UIManager.getInstance();

    private static Option nextPage(String name, AbstractPage page, String description){
        return new Option(name, ()->{
            uim.printPage(page);
        }, description);
    }

    private static Option[] mainOptions = {
        new Option("Temp", ()->{MainPage.getInstance().setMessage1("Hi :)");}, "Temp"),
        new Option("Go back to login", () -> {game.goBackToLoginPage();}, "Goes back to login page"),
        new Option("Open store page", () -> {Store.getInstance().openStorePage();},"Opens the store page")
    };

    public static Page mainPage = Page.buildPage(mainOptions, "Main Page");
}
