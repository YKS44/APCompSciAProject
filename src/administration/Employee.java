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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void addItem(int[][] loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public void removeItem(int[][] loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    @Override
    public void setPrice(double price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrice'");
    }
    
}
