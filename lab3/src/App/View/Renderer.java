package App.View;

import App.Model.Model;
import App.Utils.Buttons;
import App.View.Game.KnightView;
import App.View.Game.LevelView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;


public interface Renderer {
    void showMenu();
    void showHighScores();
    void showAbout();
    void close();
    void showWindow();
    void addMenuListener(Buttons b, ActionListener al);
    void addBackButtonListener(ActionListener al);
    void showLose(int score);
    void updateHighScores();
    void startGame(Model model);
    void updateGameView();
    void addGameListener(KeyListener listener);
    KnightView getKnightView();
    LevelView getCurrentLevelView();
}
