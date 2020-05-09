package App.Controller;

import App.Model.GameModel;
import App.Model.Model;
import App.View.Renderer;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine {
    private static final int FPS = 60;
    private final Controller controller;
    private Timer timer = null;

    public GameEngine(Renderer view, Model model) {
        controller = new GameController(model, view);
        ActionListener updater = (ActionEvent e) -> {
            controller.updateModel();
            controller.updateView();
            controller.checkGameOver(timer);
        };
        timer = new Timer(1000 / FPS, updater);
    }

    public void start() {
        controller.resetModel();
        controller.startGameView();
        timer.start();
    }
}
