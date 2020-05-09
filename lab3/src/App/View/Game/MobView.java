package App.View.Game;

import App.Model.Mob;
import App.Utils.Animations;
import App.Utils.Direction;

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

    protected MobView(Mob model, Image[] moveAnimation, Image[] attackAnimation, Image[] hurtAnimation,
                      Image[] dyingAnimation, Image[] idleAnimation, int sizeX, int sizeY) {
        this.moveAnimation = new Animation(moveAnimation, Animations.MOVING);
        this.attackAnimation = new Animation(attackAnimation, Animations.ATTACK);
        this.hurtAnimation = new Animation(hurtAnimation, Animations.HURT);
        this.dyingAnimation = new Animation(dyingAnimation, Animations.DYING);
        this.idleAnimation = new Animation(idleAnimation, Animations.IDLE);
        this.currentAnimation = this.idleAnimation;
        this.model = model;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public void changeAnimation(Animations a) {
        switch (a) {
            case MOVING: {
                currentAnimation = moveAnimation;
            }
            break;
            case IDLE: {
                currentAnimation = idleAnimation;
            }
            break;
            case HURT: {
                currentAnimation = hurtAnimation;
            }
            break;
            case DYING: {
                currentAnimation = dyingAnimation;
            }
            break;
            case ATTACK: {
                currentAnimation = attackAnimation;
            }
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

    public void resetCurrentAnimation() {
        currentAnimation.resetAnimation();
    }

    public boolean isCurrentAnimationEnded() {
        return currentAnimation.isAnimationEnded();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (model.getDirection() == Direction.RIGHT) {
            g2.drawImage(currentAnimation.getNextImage(), model.getPositionX(), model.getPositionY(),
                    sizeX, sizeY, null);
        }
        else {
            g2.drawImage(currentAnimation.getNextImage(), model.getPositionX() + sizeX / 2, model.getPositionY(),
                    -sizeX, sizeY, null);
        }
    }

    static class Animation {
        private final Image[] animation;
        private int animationStep = 0;
        private final Animations animationType;
        private static final int animationSpeedScale = 3;

        public Animation(Image[] animation, Animations type) {
            this.animation = animation;
            this.animationType = type;
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
