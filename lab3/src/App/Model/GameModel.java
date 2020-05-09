package App.Model;

import java.util.ArrayList;

public class GameModel implements Model {
    private final Knight knight;
    private final ArrayList<Level> levels = new ArrayList<>();
    private final int groundLevelHeight;
    private int score;

    public GameModel(int screenWidth, int screenHeight, int groundLevelHeight) {
        this.groundLevelHeight = groundLevelHeight;
        knight = new Knight(screenHeight - groundLevelHeight, screenWidth);
    }

    @Override
    public Knight getKnight() {
        return knight;
    }

    @Override
    public boolean isGameOver() {
        return knight.isDied();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void reset() {
        knight.reset();
        for (Level level : levels) {
            level.reset();
        }
        score = 0;
    }

    @Override
    public void update() {
        knight.update();
    }
}
