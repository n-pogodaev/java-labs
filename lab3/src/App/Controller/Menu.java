package App.Controller;

import App.Model.GameModel;
import App.Model.Model;
import App.Utils.Buttons;
import App.View.GameRenderer;
import App.View.Renderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private final GameEngine eng;
    private final Renderer view;

    public Menu() {
        Model model = new GameModel(GameRenderer.getGameLevelWidth(),
                GameRenderer.getGameLevelHeight(),
                GameRenderer.getGameLevelGroundHeight());
        view = new GameRenderer(model);
        eng = new GameEngine(view, model);
        ActionListener newGameListener = (ActionEvent e) -> {
            eng.start();
        };
        ActionListener backListener = (ActionEvent e) -> {
            view.showMenu();
        };
        ActionListener highScoresListener = (ActionEvent e) -> {
            view.showHighScores();
        };
        ActionListener aboutListener = (ActionEvent e) -> {
            view.showAbout();
        };
        ActionListener exitListener = (ActionEvent e) -> {
            view.close();
        };
        view.addMenuListener(Buttons.NEW_GAME, newGameListener);
        view.addMenuListener(Buttons.ABOUT, aboutListener);
        view.addMenuListener(Buttons.HIGH_SCORES, highScoresListener);
        view.addMenuListener(Buttons.EXIT, exitListener);
        view.addBackButtonListener(backListener);
    }
    public void start() {
        view.showWindow();
    }
}
