package App.View.Game;


import java.util.ArrayList;

public class LevelView {
    private final ArrayList<EnemyView> enemiesViews;
    public LevelView(ArrayList<EnemyView> enemiesViews) {
        this.enemiesViews = enemiesViews;
    }

    public ArrayList<EnemyView> getEnemiesViews() {
        return enemiesViews;
    }
}
