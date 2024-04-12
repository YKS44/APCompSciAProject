package ui.pages;

/**
 * The base class for all pages. A new page can be created easily with a heading, a body, and two submessages.
 */
public abstract class AbstractPage {
    private String heading = "";
    private String message1 = "";
    private String message2 = "";

    /**
     * Override this method and add print statements which will be the body of the page.
     */
    public abstract void printPage();

    /**
     * Override this method to add commands to the {@link managers.CommandManager CommandManager}.
     */
    protected abstract void setUpCommand();

    /**
     * Optional overriding.
     * Override this page for any wanted updates to variables every time the page prints. 
     */
    public void update() {}

    /**
     * Returns the heading of the page.
     * @return The heading of the page.
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Sets the heading of the page.
     * 
     * @param heading The heading of the page will be set to this parameter.
     */
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * Returns the first submessage of the page.
     * @return The first submessage of the page.
     */
    public String getMessage1() {
        return message1;
    }

    /**
     * Sets the first submessages of the page.
     * 
     * @param message1 The first submessage of the page will be set to this parameter.
     */
    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    /**
     * Returns the second submessage of the page.
     * @return The second submessage of the page.
     */
    public String getMessage2() {
        return message2;
    }

    /**
     * Sets the second submessages of the page.
     * 
     * @param message2 The second submessage of the page will be set to this parameter.
     */
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
