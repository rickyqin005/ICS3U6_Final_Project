package exception;

import javax.swing.JOptionPane;

import utility.Currency;

public class NotEnoughCurrencyException extends RuntimeException {
    public NotEnoughCurrencyException() {
    }
    public String errorString() {
        return "Not enough " + Currency.CURRENCY_NAME + "!";
    }
    public void showErrorMessage() {
        JOptionPane.showMessageDialog(null, errorString());
    }
}
