package App.View.Game;

import App.Model.Golem;
import App.View.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GolemView extends EnemyView {
    private static final Image[] attackAnimation = new Image[20];
    private static final Image[] moveAnimation = new Image[24];
    private static final Image[] hurtAnimation = new Image[12];
    private static final Image[] dyingAnimation = new Image[12];
    private static final Image[] idleAnimation = new Image[18];
    static {
        try {
            for (int i = 0; i < 24; ++i) {
                if (i < 20) {
                    attackAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Golem\\Attack\\0_Golem_Slashing_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(200, 230, 600, 530);
                }
                moveAnimation[i] = ImageIO.read(new File
                        ("src\\App\\Resources\\Golem\\Move\\0_Golem_Walking_0" +
                                i / 10 + "" + i % 10 + ".png"))
                        .getSubimage(200, 230, 500, 530);
                if (i < 18) {
                    idleAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Golem\\Idle\\0_Golem_Idle_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(200, 230, 500, 530);
                }
                if (i < 12) {
                    hurtAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Golem\\Hurt\\0_Golem_Hurt_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(160, 230, 540, 530);
                }
                if (i < 12) {
                    dyingAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\Golem\\Dying\\0_Golem_Dying_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(50, 150, 750, 680);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GolemView(Golem model) {
        super(model, moveAnimation, 2,
                attackAnimation, 3,
                hurtAnimation, 2,
                dyingAnimation, 2,
                idleAnimation, 2,
                GameRenderer.getGameLevelWidth() / 9, GameRenderer.getGameLevelHeight() / 5);
    }
}
