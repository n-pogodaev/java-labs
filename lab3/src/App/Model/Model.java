package App.Model;

import java.util.ArrayList;

public interface Model {
    Knight getKnight();
    boolean isGameOver();
    int getScore();
    void reset();
    void update();
    void knightAttack();
    ArrayList<Level> getLevels();
    int getCurrentLevelCount();
}
