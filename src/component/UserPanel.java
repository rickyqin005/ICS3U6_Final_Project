package component;
import java.awt.Graphics;

import exception.NotEnoughSimCoinsException;

public class UserPanel extends GameComponent {
    private static final int STARTING_AMOUNT = 100000;
    private String name;
    private int simCoins;

    public UserPanel(String name) {
        this.name = name;
        simCoins = UserPanel.STARTING_AMOUNT;
    }

    @Override
    public String getName() {
        return name;
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
