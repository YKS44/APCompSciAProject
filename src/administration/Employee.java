package administration;

public class Employee implements Account{
    private AccountLevel level;

    public Employee(AccountLevel level){
        this.level = level;
    }
    
    @Override
    public void move() {
        
    }

    @Override
    public void addItem(int[][] loc) {
        
    }

    @Override
    public void removeItem(int[][] loc) {
        
    }

    @Override
    public void setPrice(double price) {
        
    }

    @Override
    public AccountLevel getAccountLevel() {
        return level;
    }
    
}
