package App.Controller;

import javax.swing.Timer;

public interface Controller {
    void updateModel();
    void updateView();
    void checkGameOver(Timer timer);
    void resetModel();
    void startGameView();
}
