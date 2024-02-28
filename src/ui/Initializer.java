package ui;

public class Initializer {
    private static Initializer instance = null;

    public static Initializer getInstance(){
        if(instance == null){
            instance = new Initializer();
        }
        return instance;
    }
}
