package ui.pages;

public abstract class AbstractPage {
    private String heading = "";
    private String message1 = "";
    private String message2 = "";

    public abstract void printPage();
    protected abstract void setUpCommand();
    public void update() {}

    public String getHeading() {
        return heading;
    }
    public void setHeading(String heading) {
        this.heading = heading;
    }
    public String getMessage1() {
        return message1;
    }
    public void setMessage1(String message1) {
        this.message1 = message1;
    }
    public String getMessage2() {
        return message2;
    }
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
