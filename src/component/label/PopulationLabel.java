package component.label;

import java.awt.Font;
import javax.swing.JLabel;

import component.Grid;
import gameobject.building.ResidentialBuilding;

public class PopulationLabel extends JLabel {
    private Grid grid;
    private Font font;
    public PopulationLabel(Grid grid, Font font) {
        super();
        this.grid = grid;
        this.font = font;
        this.setText(populationToString());
        this.setFont(this.font);
    }
    public String populationToString() {
        return "Population: " + ResidentialBuilding.getTotalPopulation(grid.getBuildings());
    }
}
