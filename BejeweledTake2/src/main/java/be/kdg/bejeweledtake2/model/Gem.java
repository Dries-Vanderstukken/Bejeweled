package be.kdg.bejeweledtake2.model;

public class Gem {
    private GemColor gemColor;
    private GemMutation gemMutation;

    public Gem(GemColor gemColor, GemMutation gemMutation) {
        this.gemColor = gemColor;
        this.gemMutation = gemMutation;
    }

    public GemColor getGemColor() {
        return gemColor;
    }

    public void setGemColor(GemColor gemColor) {
        this.gemColor = gemColor;
    }

    public GemMutation getGemMutation() {
        return gemMutation;
    }

    public void setGemMutation(GemMutation gemMutation) {
        this.gemMutation = gemMutation;
    }
}
