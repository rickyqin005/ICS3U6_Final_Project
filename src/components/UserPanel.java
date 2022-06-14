package components;
import java.awt.Graphics;

import exception.NotEnoughSimCoinsException;
import utility.Sprite;

public class UserPanel extends GameComponent {
    private static final int STARTING_AMOUNT = 100000;
    private int simCoins;
    private Sprite profilePicture;

    public UserPanel() {
        simCoins = UserPanel.STARTING_AMOUNT;
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

    @Override
    public void update() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
    }
}
