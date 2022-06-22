package component.optionpane;

import javax.swing.JOptionPane;

public class ConfirmDemolishBuilding extends JOptionPane {
    private static final String NAME = "confirmDemolishBuilding";
    private static final String title = "Demolish Building";
    private static final String message = "Are you sure you want to demolish this building?";
    private int response;
    public ConfirmDemolishBuilding() {
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
