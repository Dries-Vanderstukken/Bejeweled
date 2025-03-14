package be.kdg.bejeweledtake2.model;

public class Tile {
    private Gem gem;

    private TileStatus status;

    public Gem getGem() {
        return gem;
    }

    public void setGem(Gem gem) {
        this.gem = gem;
    }

    public TileStatus getStatus() {
        return status;
    }

    public void setStatus(TileStatus status) {
        this.status = status;
    }
}
