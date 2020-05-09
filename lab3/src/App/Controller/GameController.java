package App.Controller;

import App.Model.Model;
import App.Utils.Direction;
import App.Utils.HighScore;
import App.Utils.States;
import App.View.Renderer;

import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController implements Controller {
    private final Model model;
    private final Renderer view;
    public GameController(Model model, Renderer view) {
        this.model = model;
        this.view = view;
        view.addGameListener(new MoveRightListener());
        view.addGameListener(new MoveLeftListener());
    }

    @Override
    public void updateModel() {
        model.update();
    }

    @Override
    public void updateView() {
        view.updateGameView(model);
    }

    @Override
    public void checkGameOver(Timer timer) {
        if (model.isGameOver()) {
            timer.stop();
            view.showLose(model.getScore());
            HighScore.addScore(model.getScore());
            view.updateHighScores();
        }
    }

    @Override
    public void resetModel() {
        model.reset();
    }

    @Override
    public void startGameView() {
        view.startGame(model);
    }

    public class MoveRightListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (model.getKnight().setDirection(Direction.RIGHT)) {
                    model.getKnight().setState(States.MOVING);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (model.getKnight().getState() == States.IDLE) {
                    model.getKnight().setState(States.MOVING);
                } else {
                    model.getKnight().setState(States.IDLE);
                }
            }
        }
    }

    public class MoveLeftListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (model.getKnight().setDirection(Direction.LEFT)) {
                    model.getKnight().setState(States.MOVING);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (model.getKnight().getState() == States.IDLE) {
                    model.getKnight().setState(States.MOVING);
                } else {
                    model.getKnight().setState(States.IDLE);
                }
            }
        }
    }
}
