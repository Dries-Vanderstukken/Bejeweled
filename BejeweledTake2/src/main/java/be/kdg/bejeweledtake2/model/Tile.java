package be.kdg.bejeweledtake2.model;

public class Tile {

    private Gem gem;

    private TileStatus status;

    private final int column;

    private final int row;

    public Tile(TileStatus status, int column, int row) {
        this.status = status;
        this.column = column;
        this.row = row;

    }
    public void setGem(Gem gem) {
        this.gem = gem;
    }

    public int getColumn(){
        return column;
    }
    public int getRow(){
        return row;
    }

    public Gem getGem() {
        return gem;
    }



    public TileStatus getStatus() {
        return status;
    }

    public void setStatus(TileStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ("TileAt("+column+","+row+")");
    }

}
