package component.label;

import java.awt.Graphics;
import javax.swing.JLabel;

import component.Grid;
import gameobject.building.ResidentialBuilding;
import utility.Text;

public class PopulationLabel extends JLabel {
    private static final String NAME = "populationLabel";
    private Grid grid;
    public PopulationLabel(Grid grid) {
        super();
        this.grid = grid;
        this.setText(populationToString());
        Text.formatJLabel(this);
    }
    public String populationToString() {
        return "Population: " + ResidentialBuilding.getTotalPopulation(grid.getBuildings());
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void paintComponent(Graphics g) {
        setText(populationToString());

        super.paintComponent(g);
    }
}
