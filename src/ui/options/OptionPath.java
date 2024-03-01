package ui.options;

import managers.UIManager;
import ui.pages.HomeScreen;
import ui.pages.Store;

public class OptionPath {
    private static final HomeScreen game = HomeScreen.getInstance();

    private static UIManager uim = UIManager.getInstance();

    private static Option nextPage(String name, Page page, String description){
        return new Option(name, ()->{
            uim.sendAndReceive(page);
        }, description);
    }

    private static Option[] mainOptions = {
        new Option("Temp", ()->{uim.setMessage1("Hi");}, "Temp"),
        new Option("Go back to login", () -> {game.goBackToLoginPage();}, "Goes back to login page"),
        new Option("Open store page", ()->{Store.getInstance().openStorePage();},"Opens the store page")
    };

    public static Page mainPage = Page.buildPage(mainOptions, "Main Page");
}
