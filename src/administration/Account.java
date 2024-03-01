package administration;

public interface Account {
    void move();
    void addItem(int[][] loc);
    void removeItem(int[][] loc);
    void setPrice(double price);
    AccountLevel getAccountLevel();    

    public enum AccountLevel{
        CUSTOMER(0),
        INTERN(1),
        WORKER(2),
        BOSS(3);

        public int level;

        private AccountLevel(int level){
            this.level = level;
        }
    }
}
