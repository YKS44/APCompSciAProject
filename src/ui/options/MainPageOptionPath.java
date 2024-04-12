package ui.options;

import administration.Account.AccountLevel;
import managers.LoginManager;
import managers.UIManager;
import ui.pages.AbstractPage;
import ui.pages.LoginScreen;
import ui.pages.MainPage;
import ui.pages.StoragePage;
import ui.pages.StorePage;

/**
 * The options map for the {@link ui.pages.MainPage Main page}.
 */
public class MainPageOptionPath {
    private static final LoginScreen game = LoginScreen.getInstance();

    private static UIManager uim = UIManager.getInstance();

    private static Option nextPage(String name, AbstractPage page, String description){
        return new Option(name, ()->{
            uim.printPage(page);
        }, description);
    }

    //The option path for the MainPage. 
    private static Option[] mainOptions = {
        new Option("Logout", () -> {game.goBackToLoginPage();}, "Logout"),
        new Option("Open Store page", () -> {StorePage.getInstance().openStorePage();},"Opens the store page"),
        new Option("Open Storage Page", () -> {
            if(LoginManager.getInstance().getCurrentlyLoggedIn().getAccountLevel().level >= AccountLevel.INTERN.level){
                StoragePage.getInstance().openStoragePage();
            }else{
                MainPage.getInstance().setMessage1(uim.getColoredText("red", "Customers cannot use this command."));
            }
        }, "Opens the storage page")
    };

    public static Options mainPage = Options.buildOptions(mainOptions, "Main Page");
}
