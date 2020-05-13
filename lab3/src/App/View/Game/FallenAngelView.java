package App.View.Game;

import App.Model.FallenAngel;
import App.View.GameRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FallenAngelView extends EnemyView {
    private static final Image[] attackAnimation = new Image[12];
    private static final Image[] moveAnimation = new Image[24];
    private static final Image[] hurtAnimation = new Image[12];
    private static final Image[] dyingAnimation = new Image[15];
    private static final Image[] idleAnimation = new Image[18];
    static {
        try {
            for (int i = 0; i < 24; ++i) {
                if (i < 12) {
                    attackAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\FallenAngel\\Attack\\0_Fallen_Angels_Slashing_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(200, 150, 650, 600);
                }
                moveAnimation[i] = ImageIO.read(new File
                        ("src\\App\\Resources\\FallenAngel\\Move\\0_Fallen_Angels_Walking_0" +
                                i / 10 + "" + i % 10 + ".png"))
                        .getSubimage(200, 150, 500, 600);
                if (i < 18) {
                    idleAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\FallenAngel\\Idle\\0_Fallen_Angels_Idle_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(200, 150, 500, 600);
                }
                if (i < 12) {
                    hurtAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\FallenAngel\\Hurt\\0_Fallen_Angels_Hurt_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(200, 150, 500, 600);
                }
                if (i < 15) {
                    dyingAnimation[i] = ImageIO.read(new File
                            ("src\\App\\Resources\\FallenAngel\\Dying\\0_Fallen_Angels_Dying_0" +
                                    i / 10 + "" + i % 10 + ".png"))
                            .getSubimage(40, 120, 780, 640);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FallenAngelView(FallenAngel model) {
        super(model, moveAnimation, 2,
                attackAnimation, 3,
                hurtAnimation, 2,
                dyingAnimation, 2,
                idleAnimation, 2,
                GameRenderer.getGameLevelWidth() / 9, GameRenderer.getGameLevelHeight() / 5);
    }
}
