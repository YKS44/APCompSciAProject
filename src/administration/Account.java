package administration;

public interface Account {

    /**
     * @return The account level of the account
     */
    public AccountLevel getAccountLevel();    

    /**
     * Enum of the account levels. Higher number parameter means higher account level.
     */
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
