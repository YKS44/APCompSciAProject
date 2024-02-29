package ui.options;

import ui.Game;

public class OptionPath {
    private static final Game game = Game.getInstance();

    private static Option[] mainOptions = {
        new Option("Temp", ()->{}, "Temp"),
        new Option("Go back to login", () -> {game.goBackToLoginPage();}, "Goes back to login page")
    };

    public static Page mainPage = Page.buildPage(mainOptions, "Main Page");
}
