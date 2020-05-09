package App.View.Game;

import App.Model.Model;
import App.Utils.Animations;
import App.Utils.States;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameContent extends JComponent {

    private final KnightView knight;

    public GameContent(Model model) {
        setFocusable(true);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        knight = new KnightView(model.getKnight());
        add(knight, BorderLayout.CENTER);
    }

    public void update(Model model) {
        if (model.getKnight().getState() == States.MOVING && knight.currentAnimation() != Animations.MOVING) {
            knight.changeAnimation(Animations.MOVING);
        }
        if (model.getKnight().getState() == States.IDLE && knight.currentAnimation() != Animations.IDLE) {
            knight.changeAnimation(Animations.IDLE);
        }
        if (knight.isCurrentAnimationEnded()) {
            knight.resetCurrentAnimation();
        }
        repaint();
    }
}
