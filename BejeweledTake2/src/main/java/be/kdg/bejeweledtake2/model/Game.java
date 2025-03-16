package be.kdg.bejeweledtake2.model;

import be.kdg.bejeweledtake2.view.game.GamePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    // playingField[column][row]
    // column values count from top to bottom (like a spreadsheet would :D)
    private Tile[][] playingField;
    private GameTimer timer;
    private Score scoreInstance;
    private Tile selectedTile;
    private GamePresenter presenter;
    private int matchesThisMove = 0;

    //getters & setters
    public void setPresenter(GamePresenter presenter) {
        this.presenter = presenter;
    }
    public GamePresenter getPresenter() {
        return presenter;
    }
    public Score getScore() {
        return scoreInstance;
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

    // ------------other minor methods----------------
    public void startTimer() {
        timer.startTimer();
    }

    public void randomize() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                playingField[i][j] = new Tile(TileStatus.NONE, i, j);
                playingField[i][j].setGem(new Gem(colorRandomizer(), GemMutation.NONE));
            }
        }
    }

    // Makes the board no longer have any selected tiles (does not change anything about their gems!!!)
    public void clearSelectedTile(){

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.playingField[x][y].setStatus(TileStatus.NONE);
            }
        }
    }

    // Gives us a random color when needed
    public GemColor colorRandomizer() {

        GemColor randomColor = GemColor.NONE;
        while (randomColor == GemColor.NONE) {
            randomColor = GemColor.values()[(int) (Math.random() * 8)];
        }
        return randomColor;
    }

    // When a new game is created it gets a playing field (which gets randomized after), a timer and a score
    public Game() {
        playingField = new Tile[8][8];
        timer = new GameTimer(this);
        scoreInstance = new Score();
        scoreInstance.setScore(0);
        randomize();
        while (!checkAllMatching().isEmpty()) {
            removeTiles(checkAllMatching());
        }
    }

    //---------------methods for removing tiles-----------------
    // handleRemoval: removes any matches a given tile might have and increments score/timer accordingly
    public void handleRemoval(Tile tile){
        List<Tile> tile1Matching = checkMatching(tile);
        if (tile1Matching.size() == 3) {
            removeTiles(tile1Matching);
            scoreInstance.incrementScore(30);
            timer.incrementTime(1);
        } else if (tile1Matching.size() == 4) {
            tile.getGem().setGemMutation(GemMutation.BOMB);
            tile1Matching.remove(tile);
            removeTiles(tile1Matching);
            scoreInstance.incrementScore(60);
            timer.incrementTime(2);
        } else if (tile1Matching.size() >=5) {
            tile.getGem().setGemMutation(GemMutation.HYPER_CUBE);
            tile.getGem().setGemColor(GemColor.NONE);
            tile1Matching.remove(tile);
            removeTiles(tile1Matching);
            scoreInstance.incrementScore((tile1Matching.size() == 5) ? 90 : 150);
            timer.incrementTime(3);
        }
        matchesThisMove++;
    }

    //removeTiles: actually removes the tiles you give it (between brackets like these) and fills them up nicely after (using the fillholes() method)
    public void removeTiles(List<Tile> tilesToRemove) {
        for (Tile tile : tilesToRemove) {
            tile.setStatus(TileStatus.EMPTY);
            if(tile.getGem().getGemMutation() == GemMutation.BOMB){
                List<Tile> currentBombedTiles = findBombedTiles(tile);
                for(Tile bombedTile : currentBombedTiles){
                    if (!tilesToRemove.contains(bombedTile)) {
                        scoreInstance.incrementScore(10);
                        bombedTile.setStatus(TileStatus.EMPTY);
                    }
                }
            }
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

    // Fills up the hole in the tile you give it by dropping down the gems above it as needed
    public void fillHoles(Tile filledTile) {
        int y = filledTile.getRow();
        int x = filledTile.getColumn();
        boolean continueUp = true;
        int i = 0;
        do {
            if (y-1-i >= 0){
                if (playingField[x][y - 1 - i].getStatus() != TileStatus.EMPTY) {
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

    // findBombedTiles: returns a list of tiles that are affected by a given bomb tile
    public List<Tile> findBombedTiles(Tile bombTile){
        List<Tile> bombedList = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            for( int j = -1; j < 2; j++){
                if (bombTile.getColumn()+i >= 0 && bombTile.getRow()+j >= 0 && bombTile.getColumn()+i < 8 && bombTile.getRow()+j < 8) {
                    bombedList.add(playingField[bombTile.getColumn() + i][bombTile.getRow() + j]);
                }
            }
        }
        return(bombedList);
    }

    //-----------------Methods for checking matches--------------------
    // Checks the entire board for matching tiles and returns them
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

    //checks if the given tile forms any matches and if so, returns those tiles
    public List<Tile> checkMatching(Tile tile){
        List<Tile> allTiles = new ArrayList<Tile>();

        List<Tile> horizontalMatch = checkHorizontalMatching(tile, Collections.emptyList());

        List<Tile> verticalMatch = checkVerticalMatching(tile, Collections.emptyList());

        allTiles.addAll(horizontalMatch);
        allTiles.addAll(verticalMatch);
        return allTiles;
    }

    // helper method for checking if there is a horizontal match for a given tile,
    // while ignoring tiles given through alreadyMatched
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
            return adjTilesH;
        } else {
            return Collections.emptyList();
        }
    };

    //idem. but vertical
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
            return adjTilesV;
        } else {
        return Collections.emptyList();
        }
    };

    //--------------------Player Input----------------------
    //main game logic:
    public void tileClicked(int xCoordinate,int yCoordinate){
        Tile clickedTile = playingField[xCoordinate][yCoordinate];
        int scoreBefore = scoreInstance.getScore();
        matchesThisMove = 0;
        if (clickedTile.getStatus() == TileStatus.NONE) {
            this.setSelectedTile(clickedTile);
        }
        else if (clickedTile.getStatus() == TileStatus.POSSIBLE_MOVE) {
            if (selectedTile.getGem().getGemMutation() == GemMutation.HYPER_CUBE){
                GemColor removedColor = clickedTile.getGem().getGemColor();
                handleHyperCube(removedColor);
            } else {
                swap(selectedTile, clickedTile);
                handleRemoval(selectedTile);
                matchesThisMove=0;
                handleRemoval(clickedTile);
            }
            while (!checkAllMatching().isEmpty()){
                for (Tile tile : checkAllMatching()) {
                    handleRemoval(tile);
                }
            }
            clearSelectedTile();
        }
        switch (matchesThisMove){
            case 0,1:
                break;
            case 2,3,4:
                scoreInstance.incrementScore((matchesThisMove-1)*30);
                timer.incrementTime(1);
                break;
            case 5:
                scoreInstance.incrementScore(150);
                timer.incrementTime(1);
                break;
            case 6:
                scoreInstance.incrementScore(210);
                timer.incrementTime(2);
                break;
            case 7:
                scoreInstance.incrementScore(300);
                timer.incrementTime(2);
                break;
            case 8:
                scoreInstance.incrementScore(450);
                timer.incrementTime(3);
                break;
            default:
                scoreInstance.incrementScore(600);
                timer.incrementTime(3);
                break;
        }
        int scoreAfter = scoreInstance.getScore();
        int totalMoveScore = scoreAfter - scoreBefore;
        presenter.updateScore(scoreAfter, totalMoveScore);
    }

    //checks for possible moves relevant to the selected tile
    public List<Tile> checkPossibleMoves() {
        List<Tile> possibleMoves = new ArrayList<>();
        GemColor currentSelectColor = selectedTile.getGem().getGemColor();
        int x = selectedTile.getColumn();
        int y = selectedTile.getRow();
        boolean hyperCube = (selectedTile.getGem().getGemMutation() == GemMutation.HYPER_CUBE);
        //check top
        if (y != 0) {
            Tile topTile = playingField[x][y - 1];
            GemColor topColor = topTile.getGem().getGemColor();
            topTile.getGem().setGemColor(currentSelectColor);
            selectedTile.getGem().setGemColor(topColor);

            if (!checkMatching(topTile).isEmpty() || hyperCube){
                possibleMoves.add(topTile);
            } else if (!checkMatching(selectedTile).isEmpty()){
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

            if (!checkMatching(rightTile).isEmpty() || hyperCube){
                possibleMoves.add(rightTile);
            } else if (!checkMatching(selectedTile).isEmpty()){
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

            if (!checkMatching(bottomTile).isEmpty() || hyperCube){
                possibleMoves.add(bottomTile);
            } else if (!checkMatching(selectedTile).isEmpty()){
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

            if (!checkMatching(leftTile).isEmpty() || hyperCube) {
                possibleMoves.add(leftTile);
            } else if (!checkMatching(selectedTile).isEmpty()){
                possibleMoves.add(leftTile);
            }

            selectedTile.getGem().setGemColor(currentSelectColor);
            leftTile.getGem().setGemColor(leftColor);
        }

        selectedTile.getGem().setGemColor(currentSelectColor);

        return possibleMoves;
    }

    //logic for swapping tiles
    public void swap(Tile tile1, Tile tile2) {
        GemColor tempColor;
        GemMutation tempMutation;
        tempColor = tile1.getGem().getGemColor();
        tempMutation = tile1.getGem().getGemMutation();
        tile1.getGem().setGemColor(tile2.getGem().getGemColor());
        tile1.getGem().setGemMutation(tile2.getGem().getGemMutation());
        tile2.getGem().setGemColor(tempColor);
        tile2.getGem().setGemMutation(tempMutation);
    }

    //Logic for handling hypercubes
    public void handleHyperCube(GemColor removedColor){
        List<Tile> tilesToRemove = new ArrayList<Tile>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (playingField[i][j].getGem().getGemColor() == removedColor){
                    tilesToRemove.add(playingField[i][j]);
                }
            }
        }
        tilesToRemove.add(selectedTile);
        scoreInstance.incrementScore(10*tilesToRemove.size());
        removeTiles(tilesToRemove);
    }

    //Game Over :)
    public void gameOver(){
        presenter.showHighScores();
        timer.stopTimer();
    }
}
