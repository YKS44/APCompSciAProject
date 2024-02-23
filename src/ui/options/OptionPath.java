package ui.options;

public class OptionPath {
    private static Option[] mainOptions = {
        new Option("Temp", ()->{}, "Temp")
    };

    public static Page mainPage = Page.buildPage(mainOptions, "Main Page");
}
