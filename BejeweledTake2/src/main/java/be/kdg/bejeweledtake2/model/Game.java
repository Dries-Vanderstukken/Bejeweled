package be.kdg.bejeweledtake2.model;

import be.kdg.bejeweledtake2.view.game.GamePresenter;
import be.kdg.bejeweledtake2.view.game.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    // playingField[column][row]
    // column values count from top to bottom (like a spreadsheet would :D)
    private Tile[][] playingField;
    private Timer timer;
    private Score score;
    private Tile selectedTile;

    // When a new game is created it gets a timer, a score and a playing field (which gets randomized after)
    public Game() {
        this.playingField = new Tile[8][8];
        this.timer = new Timer();
        this.score = new Score();
        this.score.setScore(0);
        this.timer.startTimer();
        randomize();
        while (!checkAllMatching().isEmpty()) {
            removeMatching(checkAllMatching());
        }
    }

    public void setSelectedTile(Tile selectedTile) {
        clearSelectedTile();
        this.selectedTile = selectedTile;
        this.selectedTile.setStatus(TileStatus.SELECTED);
        for (Tile possibleTile : checkPossibleMoves()){
            possibleTile.setStatus(TileStatus.POSSIBLE_MOVE);
        }
    }
    public Tile[][] getPlayingField() {
        return playingField;
    }

    public void clearSelectedTile(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.playingField[x][y].setStatus(TileStatus.NONE);
            }
        }
    }

    public List<Tile> checkPossibleMoves() {
        List<Tile> possibleMoves = new ArrayList<>();
        GemColor currentSelectColor = selectedTile.getGem().getGemColor();
        int x = selectedTile.getColumn();
        int y = selectedTile.getRow();

        //check top
        if (y != 0) {
            Tile topTile = playingField[x][y - 1];
            GemColor topColor = topTile.getGem().getGemColor();
            topTile.getGem().setGemColor(currentSelectColor);
            selectedTile.getGem().setGemColor(topColor);

            if (!checkMatching(topTile).isEmpty()){
                possibleMoves.add(topTile);
            }
            if (!checkMatching(selectedTile).isEmpty()){
                possibleMoves.add(topTile);
            }

            selectedTile.getGem().setGemColor(currentSelectColor);
            topTile.getGem().setGemColor(topColor);
        }
        //check right
        if (x != 7) {
            Tile rightTile = playingField[x+1][y];
            GemColor rightColor = rightTile.getGem().getGemColor();
            rightTile.getGem().setGemColor(currentSelectColor);
            selectedTile.getGem().setGemColor(rightColor);

            if (!checkMatching(rightTile).isEmpty()){
                possibleMoves.add(rightTile);
            }
            if (!checkMatching(selectedTile).isEmpty()){
                possibleMoves.add(rightTile);
            }

            selectedTile.getGem().setGemColor(currentSelectColor);
            rightTile.getGem().setGemColor(rightColor);
        }
        //check bottom
        if (y != 7) {
            Tile bottomTile = playingField[x][y + 1];
            GemColor bottomColor = bottomTile.getGem().getGemColor();
            bottomTile.getGem().setGemColor(currentSelectColor);
            selectedTile.getGem().setGemColor(bottomColor);

            if (!checkMatching(bottomTile).isEmpty()){
                possibleMoves.add(bottomTile);
            }
            if (!checkMatching(selectedTile).isEmpty()){
                possibleMoves.add(bottomTile);
            }

            selectedTile.getGem().setGemColor(currentSelectColor);
            bottomTile.getGem().setGemColor(bottomColor);
        }
        //check left
        if (x != 0) {
            Tile leftTile = playingField[x-1][y];
            GemColor leftColor = leftTile.getGem().getGemColor();
            leftTile.getGem().setGemColor(currentSelectColor);
            selectedTile.getGem().setGemColor(leftColor);

            if (!checkMatching(leftTile).isEmpty()){
                possibleMoves.add(leftTile);
            }
            if (!checkMatching(selectedTile).isEmpty()){
                possibleMoves.add(leftTile);
            }

            selectedTile.getGem().setGemColor(currentSelectColor);
            leftTile.getGem().setGemColor(leftColor);
        }

        selectedTile.getGem().setGemColor(currentSelectColor);

        return possibleMoves;
    }

    public void removeMatching(List<Tile> matchingTiles) {
        // Something fucked up about the order of this one
        for (Tile tile : matchingTiles) {
            tile.setStatus(TileStatus.EMPTY);
        }
        List<Tile> emptyTiles = new ArrayList<>();
        do {
            emptyTiles.clear();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (playingField[i][j].getStatus() == TileStatus.EMPTY) {
                        emptyTiles.add(playingField[i][j]);
                    }
                }
            }
            for (Tile tile : emptyTiles) {
                fillHoles(tile);
            }
        } while(!emptyTiles.isEmpty());
    }

    public void fillHoles(Tile filledTile) {
        int y = filledTile.getRow();
        int x = filledTile.getColumn();
        boolean continueUp = true;
        int i = 0;
        do {
            if (y-1-i >= 0){
                if (playingField[x][y-1-i].getStatus() != TileStatus.EMPTY) {
                    playingField[x][y - i].getGem().setGemColor(playingField[x][y - 1 - i].getGem().getGemColor());
                    playingField[x][y - i].getGem().setGemMutation(playingField[x][y - 1 - i].getGem().getGemMutation());
                    playingField[x][y - i].setStatus(TileStatus.NONE);
                    playingField[x][y - 1 - i].setStatus(TileStatus.EMPTY);
                    i++;
                } else {
                    i++;
                }
            } else{
                playingField[x][y-i].getGem().setGemColor(colorRandomizer());
                playingField[x][y-i].getGem().setGemMutation(GemMutation.NONE);
                playingField[x][y-i].setStatus(TileStatus.NONE);
                continueUp = false;
            }
        } while (continueUp);
    }

    public GemColor colorRandomizer() {
        GemColor randomColor = GemColor.NONE;
        while (randomColor == GemColor.NONE) {
            randomColor = GemColor.values()[(int) (Math.random() * 8)];
        }
        return randomColor;
    }

    public void randomize() {
        System.out.println("randomizing");
        for (int i = 0; i < 8; i++) {
            System.out.println(i);
            for (int j = 0; j < 8; j++) {
                System.out.println(i + " " + j);
                playingField[i][j] = new Tile(TileStatus.NONE, i, j);
                playingField[i][j].setGem(new Gem(colorRandomizer(), GemMutation.NONE));
                System.out.println(playingField[i][j].getGem().getGemColor());
            }
        }
        System.out.println("done");
    }


    public void swap(Tile tile1, Tile tile2) {
        boolean correctSwap;
            GemColor tempColor;
            GemMutation tempMutation;
            tempColor = tile1.getGem().getGemColor();
            tempMutation = tile1.getGem().getGemMutation();
            tile1.getGem().setGemColor(tile2.getGem().getGemColor());
            tile1.getGem().setGemMutation(tile2.getGem().getGemMutation());
            tile2.getGem().setGemColor(tempColor);
            tile2.getGem().setGemMutation(tempMutation);
    }

    public List<Tile> checkAllMatching() {
        List<Tile> allTiles = new ArrayList<Tile>();
        List<Tile> alreadyMatchedH = new ArrayList<Tile>();
        List<Tile> alreadyMatchedV = new ArrayList<Tile>();
        for (Tile[] column : playingField) {
            for (Tile tile : column) {
                List<Tile> horizontalMatch = checkHorizontalMatching(tile, alreadyMatchedH);
                alreadyMatchedH.addAll(horizontalMatch);

                List<Tile> verticalMatch = checkVerticalMatching(tile, alreadyMatchedV);
                alreadyMatchedV.addAll(verticalMatch);
            }
        }
        allTiles.addAll(alreadyMatchedH);
        allTiles.addAll(alreadyMatchedV);
        return allTiles;
    }

    public List<Tile> checkMatching(Tile tile){
        List<Tile> allTiles = new ArrayList<Tile>();

        List<Tile> horizontalMatch = checkHorizontalMatching(tile, Collections.emptyList());

        List<Tile> verticalMatch = checkVerticalMatching(tile, Collections.emptyList());

        allTiles.addAll(horizontalMatch);
        allTiles.addAll(verticalMatch);
        return allTiles;
    }

    public List<Tile> checkHorizontalMatching(Tile tile, List<Tile> alreadyMatched) {
        int x = tile.getColumn();
        int y = tile.getRow();
        int amountAdjGemsHorizontal = 1;

        List<Tile> adjTilesH = new ArrayList<Tile>();
        //left
        int i = 1;
        boolean continueleft = true;
        do {
            if (x - i >= 0) {
                if (!alreadyMatched.contains(playingField[x - i][y]) && playingField[x - i][y].getGem().getGemColor() == tile.getGem().getGemColor()) {
                    amountAdjGemsHorizontal += 1;
                    adjTilesH.add(playingField[x - i][y]);
                    i += 1;
                } else {
                    continueleft = false;
                }
            } else {
                continueleft = false;
            }
        } while (continueleft);
        //right
        i = 1;
        boolean continueright = true;
        do {
            if (x + i <= 7) {
                if (!alreadyMatched.contains(playingField[x + i][y]) && playingField[x + i][y].getGem().getGemColor() == tile.getGem().getGemColor()) {
                    amountAdjGemsHorizontal += 1;
                    adjTilesH.add(playingField[x + i][y]);
                    i += 1;
                } else {
                    continueright = false;
                }
            } else {
                continueright = false;
            }

        } while (continueright);

        if (amountAdjGemsHorizontal >= 3) {
            adjTilesH.add(tile);
            System.out.println("remove these tiles: " + adjTilesH);
            return adjTilesH;
        } else {
            return Collections.emptyList();
        }
    };

    public List<Tile> checkVerticalMatching(Tile tile, List<Tile> alreadyMatched) {

        int x = tile.getColumn();
        int y = tile.getRow();
        int amountAdjGemsVertical = 1;

        List<Tile> adjTilesV = new ArrayList<Tile>();
        //top
        int i = 1;
        boolean continuetop = true;
        do{
            if(y-i>= 0) {
                if (!alreadyMatched.contains(playingField[x][y-i]) && playingField[x][y-i].getGem().getGemColor() == tile.getGem().getGemColor()) {
                    amountAdjGemsVertical += 1;
                    adjTilesV.add(playingField[x][y-i]);
                    i += 1;
                } else {
                    continuetop = false;
                }
            } else {
                continuetop = false;
            }
        }while(continuetop);
        //Bottom
        i = 1;
        boolean continuebottom = true;
        do {
            if (y + i <= 7) {
                if (!alreadyMatched.contains(playingField[x][y+i]) && playingField[x][y + i].getGem().getGemColor() == tile.getGem().getGemColor()) {
                    amountAdjGemsVertical += 1;
                    adjTilesV.add(playingField[x][y + i]);
                    i += 1;
                } else {
                    continuebottom = false;
                }
            } else {
            continuebottom = false;
        }
        }while(continuebottom);

        if (amountAdjGemsVertical >= 3){
            adjTilesV.add(tile);
            System.out.println("remove these tiles: "+adjTilesV);
            return adjTilesV;
        } else {
        return Collections.emptyList();
        }
    };

    public void checkSurroundings(int x, int y){
        Tile checkingTile = playingField[x][y];
        for (Tile[] column : playingField){
            for (Tile currentTile : column){
                System.out.println("Checking column "+ currentTile.getColumn() +" Row " + currentTile.getRow());
                if(currentTile.getGem().getGemColor() == checkingTile.getGem().getGemColor()){
                    System.out.println("We've got one of the same colour! It's in row " + currentTile.getRow() + " on tile " + currentTile.getColumn() );
                }
            }
        }
    }

    public void printField(){
        System.out.println("------Field------");
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length; j++) {

                System.out.print(playingField[i][j].getGem().getGemColor() + " \t \t");
            }
            System.out.println("\n");
        }
    }

    public void tileClicked(int xCoordinate,int yCoordinate){
        Tile clickedTile = playingField[xCoordinate][yCoordinate];
        if (clickedTile.getStatus() == TileStatus.NONE) {
            this.setSelectedTile(clickedTile);
        }
        else if (clickedTile.getStatus() == TileStatus.POSSIBLE_MOVE) {
            swap(selectedTile, clickedTile);
            handleRemoval(selectedTile);
            handleRemoval(clickedTile);
            while (!checkAllMatching().isEmpty()){
                for (Tile tile : checkAllMatching()) {
                    handleRemoval(tile);
                }
            }
            clearSelectedTile();
        }
    }

    public void handleRemoval(Tile tile){
        List<Tile> tile1Matching = checkMatching(tile);
        System.out.println(tile1Matching.size());
        if (tile1Matching.size() == 3) {
            removeMatching(tile1Matching);
            score.incrementScore(30);
        } else if (tile1Matching.size() == 4) {
            tile.getGem().setGemMutation(GemMutation.BOMB);
            tile1Matching.remove(tile);
            removeMatching(tile1Matching);
            score.incrementScore(60);
        } else if (tile1Matching.size() >=5) {
            tile.getGem().setGemMutation(GemMutation.HYPER_CUBE);
            tile.getGem().setGemColor(GemColor.NONE);
            tile1Matching.remove(tile);
            removeMatching(tile1Matching);
            score.incrementScore(20*tile1Matching.size());
        } else {
            removeMatching(tile1Matching);
        }
    }
}
