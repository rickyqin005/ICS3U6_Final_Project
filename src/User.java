public class User {
    private static final int STARTING_AMOUNT = 100000;
    private int simCoins;
    private Sprite profilePicture;

    public User() {
        simCoins = User.STARTING_AMOUNT;
    }
    public void addMoney(int amount) {
        simCoins += amount;
    }
    public void spendMoney(int amount) throws NotEnoughSimCoinsException {
        if(amount > simCoins) {
            throw new NotEnoughSimCoinsException();
        } else {
            simCoins -= amount;
        }
    }
}
