package administration;

public interface Account {
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
