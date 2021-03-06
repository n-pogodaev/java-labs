package App.View.Game;

import App.Model.Knight;
import App.View.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KnightView extends MainHeroView {
    private static final Image[] attackAnimation = new Image[10];
    private static final Image[] moveAnimation = new Image[10];
    private static final Image[] hurtAnimation = new Image[10];
    private static final Image[] dyingAnimation = new Image[31];
    private static final Image[] idleAnimation = new Image[10];

    static {
        try {
            for (int i = 0; i < 31; ++i) {
                if (i < 10) {
                    attackAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Knight\\Attack\\Knight_01__ATTACK_00" + i + ".png"))
                            .getSubimage(710, 170, 740, 520);
                    moveAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Knight\\Move\\Knight_01__WALK_00" + i + ".png"))
                            .getSubimage(710, 170, 715, 520);
                    idleAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Knight\\Idle\\Knight_01__IDLE_00" + i + ".png"))
                            .getSubimage(710, 170, 715, 520);
                    hurtAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Knight\\Hurt\\Knight_01__HURT_00" + i + ".png"))
                            .getSubimage(710, 170, 715, 520);
                }
                dyingAnimation[i] = ImageIO.read(new File
                        ("src\\App\\Resources\\Knight\\Dying\\Knight_01__DIE_0" + i / 10 + "" + i % 10 + ".png"))
                        .getSubimage(580, 170, 720, 520);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightView(Knight model) {
        super(model, moveAnimation, 2,
                attackAnimation, 2,
                hurtAnimation, 2,
                dyingAnimation, 3,
                idleAnimation, 2,
                GameRenderer.getGameLevelWidth() / 6 , GameRenderer.getGameLevelHeight() / 5);
    }
}
