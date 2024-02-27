package administration;

public interface Account {
    void move();
    void addItem(int[][] loc);
    void removeItem(int[][] loc);
    void setPrice(double price);
}
