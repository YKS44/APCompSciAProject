package administration;

public interface Employee {
    void move();
    void addItem(int[][] loc);
    void removeItem(int[][] loc);
    void setPrice(double price);
}
