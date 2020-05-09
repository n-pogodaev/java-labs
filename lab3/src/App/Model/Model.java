package App.Model;

public interface Model {
    Knight getKnight();
    boolean isGameOver();
    int getScore();
    void reset();
    void update();
}
