package administration;

public class Employee implements Account{

    public enum EmployeeLevel{
        INTERN,
        WORKER,
        BOSS
    }

    private EmployeeLevel level;

    public Employee(EmployeeLevel level){
        this.level = level;
    }

    public EmployeeLevel getEmployeeLevel(){
        return level;
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
    
}
