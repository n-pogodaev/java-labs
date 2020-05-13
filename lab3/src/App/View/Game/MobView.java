package App.View.Game;

import App.Model.Mob;
import App.Utils.Animations;
import App.Utils.States;

import javax.swing.*;
import java.awt.*;

public abstract class MobView extends JComponent {
    private final Animation moveAnimation;
    private final Animation attackAnimation;
    private final Animation idleAnimation;
    private final Animation dyingAnimation;
    private final Animation hurtAnimation;
    private final Mob model;
    private Animation currentAnimation;
    private final int sizeX;
    private final int sizeY;

    protected MobView(Mob model, Image[] moveAnimation, int moveAnimationSpeedScale,
                      Image[] attackAnimation, int attackAnimationSpeedScale,
                      Image[] hurtAnimation, int hurtAnimationSpeedScale,
                      Image[] dyingAnimation, int dyingAnimationSpeedScale,
                      Image[] idleAnimation, int idleAnimationSpeedScale,
                      int sizeX, int sizeY) {
        this.moveAnimation = new Animation(moveAnimation, Animations.MOVING, moveAnimationSpeedScale);
        this.attackAnimation = new Animation(attackAnimation, Animations.ATTACK, attackAnimationSpeedScale);
        this.hurtAnimation = new Animation(hurtAnimation, Animations.HURT, hurtAnimationSpeedScale);
        this.dyingAnimation = new Animation(dyingAnimation, Animations.DYING, dyingAnimationSpeedScale);
        this.idleAnimation = new Animation(idleAnimation, Animations.IDLE, idleAnimationSpeedScale);
        this.currentAnimation = this.idleAnimation;
        this.model = model;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Mob getModel() {
        return model;
    }

    public void update() {
        if (model.getState() == States.MOVING) {
            if (currentAnimation() != Animations.MOVING) {
                changeAnimation(Animations.MOVING);
            }
            else if (isCurrentAnimationEnded()) {
                resetCurrentAnimation();
            }
        }
        if (model.getState() == States.IDLE) {
            if (currentAnimation() != Animations.IDLE) {
                changeAnimation(Animations.IDLE);
            }
            else if (isCurrentAnimationEnded()) {
                resetCurrentAnimation();
            }
        }
        if (model.getState() == States.ATTACKING && currentAnimation() != Animations.ATTACK) {
            changeAnimation(Animations.ATTACK);
        }
        if (model.getState() == States.TAKING_DAMAGE && currentAnimation() != Animations.HURT) {
            changeAnimation(Animations.HURT);
        }
        if (model.getState() == States.DIED && currentAnimation() != Animations.DYING) {
            changeAnimation(Animations.DYING);
        }
    }

    public void changeAnimation(Animations a) {
        switch (a) {
            case MOVING:
                currentAnimation = moveAnimation;
                break;
            case IDLE:
                currentAnimation = idleAnimation;
                break;
            case HURT:
                currentAnimation = hurtAnimation;
                break;
            case DYING:
                currentAnimation = dyingAnimation;
                break;
            case ATTACK:
                currentAnimation = attackAnimation;
                break;
            default: {
                currentAnimation = null;
            }
        }
        currentAnimation.resetAnimation();
    }

    public Animations currentAnimation() {
        return currentAnimation.getAnimationType();
    }

    private void resetCurrentAnimation() {
        currentAnimation.resetAnimation();
    }

    public boolean isCurrentAnimationEnded() {
        return currentAnimation.isAnimationEnded();
    }

    public Image getNextImageToDraw() {
        return currentAnimation.getNextImage();
    }

    static class Animation {
        private final Image[] animation;
        private int animationStep = 0;
        private final Animations animationType;
        private final int animationSpeedScale;
        public Animation(Image[] animation, Animations type, int animationSpeedScale) {
            this.animation = animation;
            this.animationType = type;
            this.animationSpeedScale = animationSpeedScale;
        }

        public void resetAnimation() {
            animationStep = 0;
        }

        public boolean isAnimationEnded() {
            return (animationStep >= animation.length * animationSpeedScale);
        }

        public Animations getAnimationType() {
            return animationType;
        }

        public Image getNextImage() {
            if (animationStep >= animation.length * animationSpeedScale) {
                return animation[animation.length - 1];
            }
            Image result = animation[animationStep / animationSpeedScale];
            ++animationStep;
            return result;
        }
    }
}
