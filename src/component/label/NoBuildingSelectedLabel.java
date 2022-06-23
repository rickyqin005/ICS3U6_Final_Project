package component.label;

public class NoBuildingSelectedLabel extends GameLabel {
    private static final String NAME = "noBuildingSelectedLabel";
    private static final String TEXT = "No building selected";

    public NoBuildingSelectedLabel() {
        super(TEXT);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
    }
}
