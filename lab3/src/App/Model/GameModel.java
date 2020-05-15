package App.Model;

import App.Utils.Direction;
import App.Utils.States;

import java.util.ArrayList;

public class GameModel implements Model {
    private final Knight knight;
    private final ArrayList<Level> levels = new ArrayList<>();
    private final int screenWidth;
    private Level currentLevel;
    private int currentLevelCount = 0;
    private final int screenHeight;
    private final int groundLevelHeight;
    private int score = 0;

    public GameModel(int screenWidth, int screenHeight, int groundLevelHeight) {
        this.groundLevelHeight = groundLevelHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        knight = new Knight(screenHeight - groundLevelHeight, screenWidth);
        initLevels();
        currentLevel = levels.get(currentLevelCount);
    }

    @Override
    public Knight getKnight() {
        return knight;
    }

    @Override
    public void knightAttack() {
        Direction knightDirection = knight.getDirection();
        for (Enemy e : currentLevel.getEnemies()) {
            if (!e.isDied()) {
                int distance = e.getPositionX() - knight.getPositionX();
                if (knightDirection == Direction.RIGHT && distance > 0 && distance <= knight.getAttackRange() ||
                        knightDirection == Direction.LEFT && distance < 0 && -distance <= knight.getAttackRange()) {
                    e.takeDamage(knight.getAttack());
                    if (e.isDied()) {
                        e.setState(States.DIED);
                        score += 100;
                    } else {
                        e.setState(States.TAKING_DAMAGE);
                    }
                }
            }
        }
    }

    private void initLevels() {
        ArrayList<Enemy> level1Enemies = new ArrayList<>();
        level1Enemies.add(new Golem(knight, screenWidth / 2, screenHeight - groundLevelHeight, screenWidth));
        level1Enemies.add(new Golem(knight, screenWidth / 2 + 100, screenHeight - groundLevelHeight, screenWidth));
        level1Enemies.add(new Golem(knight, screenWidth / 2 + 200, screenHeight - groundLevelHeight, screenWidth));
        levels.add(new Level(level1Enemies, knight, screenWidth));
        ArrayList<Enemy> level2Enemies = new ArrayList<>();
        level2Enemies.add(new FallenAngel(knight, screenWidth / 2, screenHeight - groundLevelHeight, screenWidth));
        level2Enemies.add(new FallenAngel(knight, screenWidth / 2 + 100, screenHeight - groundLevelHeight, screenWidth));
        level2Enemies.add(new FallenAngel(knight, screenWidth / 2 + 200, screenHeight - groundLevelHeight, screenWidth));
        levels.add(new Level(level2Enemies, knight, screenWidth));
        ArrayList<Enemy> level3Enemies = new ArrayList<>();
        level3Enemies.add(new Reaper(knight, screenWidth / 3 * 2, screenHeight - groundLevelHeight, screenWidth));
        levels.add(new Level(level3Enemies, knight, screenWidth));
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
        currentLevel = levels.get(0);
        currentLevelCount = 0;
        score = 0;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    @Override
    public int getCurrentLevelCount() {
        return currentLevelCount;
    }

    @Override
    public void update() {
        currentLevel.update();
        if (currentLevel.isNextLevel()) {
            knight.setPositionX(0);
            if (currentLevelCount < levels.size() - 1) {
                currentLevel = levels.get(++currentLevelCount);
            }
            else {
                knight.takeDamage(1000);
                knight.setState(States.DIED);
            }
        }
    }
}
