package App.Model;

import App.Utils.States;

import java.util.ArrayList;

public class Level {
    private final ArrayList<Enemy> enemies;
    private final Knight knight;
    private final int levelWidth;
    public Level(ArrayList<Enemy> enemies, Knight knight, int levelWidth) {
        this.enemies = enemies;
        this.knight = knight;
        this.levelWidth = levelWidth;
    }

    public void update() {
        knight.update();
        for (Enemy e : enemies) {
            if (e.getState() != States.TAKING_DAMAGE && e.getState() != States.DIED && e.getState() != States.ATTACKING) {
                if (e.attack()) {
                    e.setState(States.ATTACKING);
                }
            }
            e.update();
        }
    }

    public boolean isNextLevel() {
        return knight.getPositionX() >= levelWidth;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    void reset() {
        for (Enemy enemy: enemies) {
            enemy.reset();
        }
    }
}
