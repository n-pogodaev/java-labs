package App.Model;

import java.util.ArrayList;

public class Level {
    private final ArrayList<Enemy> enemies;
    public Level(ArrayList<Enemy> enemies, Knight knight) {
        this.enemies = enemies;
    }

    public void update() {

    }

    void reset() {
        for (Enemy enemy: enemies) {
            enemy.reset();
        }
    }
}
