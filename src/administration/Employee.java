package administration;

public class Employee implements Account{
    private AccountLevel level;

    public Employee(AccountLevel level){
        this.level = level;
    }

    @Override
    public AccountLevel getAccountLevel() {
        return level;
    }
    
}
