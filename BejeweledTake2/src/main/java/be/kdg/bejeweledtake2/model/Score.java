package be.kdg.bejeweledtake2.model;

import java.io.*;
import java.util.ArrayList;
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

    public void tryNewWriter() throws IOException {
        try {
            highScoreWriter = new FileWriter("high_scores.csv", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void tryNewReader() throws IOException {
        try {
            highScoreReader = new FileReader("high_scores.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeScoreToFile(String username, int score) throws IOException {
        try {
            scoreClassSetup();
            highScoreWriter.write(username + ";" + score);
            highScoreWriter.write("\n");
            highScoreWriter.close();
        } catch(IOException e) {
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

    public List<Score> createHighScoreList() throws IOException {
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

    public void clearFile() throws IOException {
        new FileWriter(highScoresFile, false).close();
    }

    public List<Score> handleHighscores() throws IOException {
        List<Score> list = Collections.emptyList();
        try {
            if (!highScoresFile.exists()) {
                highScoresFile.createNewFile();
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
        }
        catch (IOException e) {
        }
        return list;
    }

}
