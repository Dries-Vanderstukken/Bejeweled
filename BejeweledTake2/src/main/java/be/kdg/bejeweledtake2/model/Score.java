package be.kdg.bejeweledtake2.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Score {
    private int scoreNumber;

    private String playerName;

    File highScoresFile = new File("high_scores.csv");

    FileWriter highScoreWriter;

    FileReader highScoreReader;


    public Score(int score, String playerName) {
        this.scoreNumber = score;
        this.playerName = playerName;

    }
    public Score() {
        playerName = "default";
        scoreNumber = 1;

        // Testing grounds underneath:

    }

    public void scoreClassSetup() throws IOException {
        tryNewWriter();
        tryNewReader();
    }

    public void setScore(int score) {
        this.scoreNumber = score;
    }

    public int getScore() {
        return scoreNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void addNewScore() {

    }
    public void tryNewWriter() throws IOException {
        try {
            highScoreWriter = new FileWriter("high_scores.csv", true);
        } catch (IOException e) {
            System.out.println("Something went wrong while creating the file writer");
            throw new RuntimeException(e);
        }

    }

    public void tryNewReader() throws IOException {
        try {
            highScoreReader = new FileReader("high_scores.csv");
        } catch (IOException e) {
            System.out.println("Something went wrong while creating the file reader");
            throw new RuntimeException(e);
        }
    }


    public void writeScoreToFile(String username, int score) throws IOException {
        try {
            scoreClassSetup();
            highScoreWriter.write(username + ";" + score);
            highScoreWriter.write("\n");
            highScoreWriter.close();
            System.out.println("Score done writing");
            System.out.println(username + ";" + score);
        } catch(IOException e) {
            System.out.println("Something went wrong with the writer while writing score");
        }
    }



    public void incrementScore(int i) {
        scoreNumber += i;
    }

    public List<String> readUsernamesFromFile() throws IOException {
        List<String> usernames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("high_scores.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                usernames.add(values[0]);

            }
        }
        return usernames;
    }

    public List<Integer> readScoresFromFile() throws IOException {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("high_scores.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                scores.add(Integer.valueOf(values[1]));

            }
        }
        return scores;
    }

    public void readCombinedScoresFromFile() throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("high_scores.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
                System.out.println(line);
                if(values.length >= 2) {
                    System.out.println("Username: " + values[0]);
                    System.out.println("Score: " + values[1]);
                }
            }
        }
    }

    public List<Score> createHighScoreList() throws IOException {
        System.out.println(readScoresFromFile());
        List <Score> highScoreList = new ArrayList<>();
        boolean alreadyHighScore = false;
        for (int i = 0; i < 10; i++ ) {
            if (alreadyHighScore) {
                highScoreList.add(new Score(readScoresFromFile().get(i-1), readUsernamesFromFile().get(i-1)));
            } else {
                if (readScoresFromFile().get(i) < scoreNumber) {
                    highScoreList.add(new Score(scoreNumber, playerName));
                    alreadyHighScore = true;
                } else {
                    highScoreList.add(new Score(readScoresFromFile().get(i), readUsernamesFromFile().get(i)));
                }
            }
        }
        return highScoreList;
    }

    public void deleteCurrentHighScoresFile(){
        highScoresFile.delete();
        if(!highScoresFile.exists()) {
            System.out.println("File deleted correctly");
        } else {
            System.out.println("File couldn't be deleted");
        }
    }

    public void clearFile() throws IOException {
        new FileWriter(highScoresFile, false).close();
    }

    public void fileCreator() throws IOException {
        if(highScoresFile.exists()) {
            System.out.println("File already exists");
        } else {
            System.out.println("File doesn't exist yet");
        }

        highScoresFile.createNewFile();
        if(highScoresFile.exists()) {
            System.out.println("File now exists");
        } else {
            System.out.println("File doesn't exist");
        }
    }

    public List<Score> handleHighscores() throws IOException {
        List<Score> list = Collections.emptyList();
        try {
            System.out.println("Checking if we need a new file");
            if (!highScoresFile.exists()) {
                highScoresFile.createNewFile();
                System.out.println("A new high scores file had to be created");
            }
            if (readScoresFromFile().size() != 10) {
                clearFile();
                for (int i = 0; i<10; i++) {
                    writeScoreToFile("None",0);
                }
            }
            list = createHighScoreList();
            clearFile();
            for (Score score : list) {
                writeScoreToFile(score.getPlayerName(), score.getScore());
            }
            System.out.println(list);
        }
        catch (FileNotFoundException e) {
            System.out.println("NoFileFoundError");
        }
        catch (IOException e) {
            System.out.println("HandleHighscoreError");
        }
        return list;
    }

}
