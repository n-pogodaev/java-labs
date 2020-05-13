package App.View.Game;

import App.Model.Mob;

import java.awt.*;

public class EnemyView extends MobView {
    protected EnemyView(Mob model, Image[] moveAnimation, int moveAnimationSpeedScale,
                        Image[] attackAnimation, int attackAnimationSpeedScale,
                        Image[] hurtAnimation, int hurtAnimationSpeedScale,
                        Image[] dyingAnimation, int dyingAnimationSpeedScale,
                        Image[] idleAnimation, int idleAnimationSpeedScale,
                        int sizeX, int sizeY) {
        super(model, moveAnimation, moveAnimationSpeedScale, attackAnimation, attackAnimationSpeedScale, hurtAnimation, hurtAnimationSpeedScale, dyingAnimation, dyingAnimationSpeedScale, idleAnimation, idleAnimationSpeedScale, sizeX, sizeY);
    }
}
