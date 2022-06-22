package component.optionpane;

import javax.swing.JOptionPane;

public class SaveGameOption extends JOptionPane {
    private static final String NAME = "saveGameOption";
    private static final String title = "Save Game";
    private static final String message = "Do you want to save your game progress?";
    private int response;
    public SaveGameOption() {
        response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    public int getResponse() {
        return response;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
