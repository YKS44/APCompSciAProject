package administration;

public class Employee implements Account{
    private AccountLevel level;

    /**
     * Creates a new instance of an Employee.
     * 
     * @param level The account level of the employee.
     */
    public Employee(AccountLevel level){
        this.level = level;
    }

    @Override
    public AccountLevel getAccountLevel() {
        return level;
    }
    
}
