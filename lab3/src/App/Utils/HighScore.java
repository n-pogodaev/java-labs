package App.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HighScore {
    private static final String scoresFileName = "src\\App\\Utils\\highScores.txt";
    private static final int scoresCount = 10;
    private static final int[] scores = new int[scoresCount];

    static {
        readScores();
    }

    private static void readScores() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(scoresFileName)));
            for (int i = 0; i < scoresCount; ++i) {
                scores[i] = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private static void writeScores() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(scoresFileName)));
            for (int i = 0; i < scoresCount; ++i) {
                writer.write(scores[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static void addScore(int score) {
        if (score < scores[scoresCount - 1]) {
            return;
        }
        if (score > scores[0]) {
            for (int i = scoresCount - 1; i > 0; --i) {
                scores[i] = scores[i - 1];
            }
            scores[0] = score;
        }
        for (int i = scoresCount - 1; i > 0; --i) {
            if (score > scores[i] && score < scores[i - 1]) {
                for (int j = scoresCount - 1; j > i; --j) {
                    scores[j] = scores[j - 1];
                }
                scores[i] = score;
                break;
            }
        }
        writeScores();
    }

    public static int[] getScore() {
        return Arrays.copyOf(scores, scoresCount);
    }

    public static void updateScore(int[] scs) {
        if (scs.length != scoresCount) {
            return;
        }
        System.arraycopy(scores, 0, scs, 0, scoresCount);
    }

    public static int GetScoresCount() {
        return scoresCount;
    }
}
