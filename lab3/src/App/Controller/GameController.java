package App.Controller;

import App.Model.Model;
import App.Utils.Animations;
import App.Utils.Direction;
import App.Utils.HighScore;
import App.Utils.States;
import App.View.Game.MainHeroView;
import App.View.Game.MobView;
import App.View.Renderer;

import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController implements Controller {
    private final Model model;
    private final Renderer view;
    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;

    public GameController(Model model, Renderer view) {
        this.model = model;
        this.view = view;
        view.addGameListener(new GameKeyListener());
    }

    @Override
    public void updateModel() {
        checkAttackingOrTakingDamage(view.getKnightView());
        for (MobView mv : view.getCurrentLevelView().getEnemiesViews()) {
            checkAttackingOrTakingDamage(mv);
        }
        model.update();
    }

    private void checkAttackingOrTakingDamage(MobView mobView) {
        if ((mobView.getModel().getState() == States.ATTACKING &&
                mobView.currentAnimation() == Animations.ATTACK ||
                mobView.getModel().getState() == States.TAKING_DAMAGE &&
                mobView.currentAnimation() == Animations.HURT) &&
                mobView.isCurrentAnimationEnded()
        ) {
            if (mobView instanceof MainHeroView) {
                if (rightKeyPressed && leftKeyPressed || !rightKeyPressed && !leftKeyPressed) {
                    mobView.getModel().setState(States.IDLE);
                } else {
                    if (rightKeyPressed) {
                        mobView.getModel().setDirection(Direction.RIGHT);
                    } else {
                        mobView.getModel().setDirection(Direction.LEFT);
                    }
                    mobView.getModel().setState(States.MOVING);
                }
            }
            else {
                mobView.getModel().setState(States.MOVING);
            }
        }
    }

    @Override
    public void updateView() {
        view.updateGameView();
    }

    @Override
    public void checkGameOver(Timer timer) {
        if (model.isGameOver() &&
                view.getKnightView().currentAnimation() == Animations.DYING &&
                view.getKnightView().isCurrentAnimationEnded()) {
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

    private boolean canMoveOrBeIdle() {
        States st = model.getKnight().getState();
        return (st != States.ATTACKING && st != States.TAKING_DAMAGE && st != States.DIED);
    }

    private void setKnightMovingOrIdle(States movingOrIdle) {
        if (canMoveOrBeIdle()) {
            if (movingOrIdle == States.MOVING || movingOrIdle == States.IDLE) {
                model.getKnight().setState(movingOrIdle);
            }
        }
    }

    public class GameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (!leftKeyPressed) {
                    setKnightMovingOrIdle(States.MOVING);
                    model.getKnight().setDirection(Direction.RIGHT);
                }
                else {
                    setKnightMovingOrIdle(States.IDLE);
                }
                rightKeyPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (!rightKeyPressed) {
                    setKnightMovingOrIdle(States.MOVING);
                    model.getKnight().setDirection(Direction.LEFT);
                }
                else {
                    setKnightMovingOrIdle(States.IDLE);
                }
                leftKeyPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                if (model.getKnight().getState() != States.TAKING_DAMAGE &&
                        model.getKnight().getState() != States.DIED && model.getKnight().getState() != States.ATTACKING) {
                    model.knightAttack();
                    model.getKnight().setState(States.ATTACKING);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (leftKeyPressed) {
                    setKnightMovingOrIdle(States.MOVING);
                    model.getKnight().setDirection(Direction.LEFT);
                }
                else {
                    setKnightMovingOrIdle(States.IDLE);
                }
                rightKeyPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (rightKeyPressed) {
                    setKnightMovingOrIdle(States.MOVING);
                    model.getKnight().setDirection(Direction.RIGHT);
                }
                else {
                    setKnightMovingOrIdle(States.IDLE);
                }
                leftKeyPressed = false;
            }
        }
    }
}
