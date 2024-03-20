package ui.options;

import managers.UIManager;
import ui.pages.AbstractPage;
import ui.pages.LoginScreen;
import ui.pages.StoragePage;
import ui.pages.StorePage;

public class MainPageOptionPath {
    private static final LoginScreen game = LoginScreen.getInstance();

    private static UIManager uim = UIManager.getInstance();

    private static Option nextPage(String name, AbstractPage page, String description){
        return new Option(name, ()->{
            uim.printPage(page);
        }, description);
    }

    private static Option[] mainOptions = {
        new Option("Logout", () -> {game.goBackToLoginPage();}, "Logout"),
        new Option("Open Store page", () -> {StorePage.getInstance().openStorePage();},"Opens the store page"),
        new Option("Open Storage Page", () -> {StoragePage.getInstance().openStoragePage();}, "Opens the storage page")
    };

    public static Page mainPage = Page.buildPage(mainOptions, "Main Page");
}
