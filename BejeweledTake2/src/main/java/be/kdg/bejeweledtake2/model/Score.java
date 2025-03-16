package be.kdg.bejeweledtake2.model;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Score {
    private int score;

    private String playerName;

    File highScores = new File("high_scores.txt");

    FileWriter highScoreWriter;

    FileReader highScoreReader;


    public Score() throws FileNotFoundException, IOException {
        try {
            score = 0;
            this.tryNewWriter();
            tryFileCreation();
            tryNewWriter();
            tryNewReader();
            // Testing grounds underneath:
            setScore(1000);
            writeScoreToFile();
            System.out.println("Score here:");
            readScoresFromFile();
        }
        catch (FileNotFoundException e) {
            System.out.println("No file here son");
        }
        catch (IOException e) {
            System.out.println("Something wrong with the score constructor");
        }
    }



    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void addNewScore() {

    }


    public void tryFileCreation() throws IOException {
        try {

            if (highScores.createNewFile()) {
                System.out.println("File created:" + highScores.getName() + " at " + highScores.getAbsolutePath());

            } else {
                System.out.println("File already exists:" + highScores.getName() + " at " + highScores.getAbsolutePath());
            }
        } catch (IOException e){
            System.out.println("Something went wrong while creating the high scores file");
            e.printStackTrace();
        }
    }

    public void tryNewWriter() throws IOException {
        try {
            highScoreWriter = new FileWriter("high_scores.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong while creating the file writer");
            throw new RuntimeException(e);
        }

    }

    public void tryNewReader() throws IOException {
        try {
            highScoreReader = new FileReader("high_scores.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong while creating the file reader");
            throw new RuntimeException(e);
        }
    }


    public void writeScoreToFile() throws IOException {
        highScoreWriter.write(this.playerName + ";" + this.score);
        highScoreWriter.write("\n");
        highScoreWriter.close();
    }



    public void incrementScore(int i) {
        score += i;
    }



    public void readScoresFromFile() throws FileNotFoundException{

            try {
                Scanner myReader = new Scanner(highScores);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

    }

}
