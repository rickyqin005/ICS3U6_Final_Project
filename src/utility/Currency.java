package utility;

public class Currency {
    public static final String CURRENCY_NAME = "Simcoins";
    public static final String CURRENCY_SYMBOL = String.valueOf('\u00A7');
    
    public static String formatCurrencyAmount(int amount) {
        return CURRENCY_SYMBOL + String.format("%,d", amount);
    }
}
