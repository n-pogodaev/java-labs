package App.View.Game;

import App.Model.*;
import App.Utils.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameContent extends JComponent {
    private final KnightView knight;
    private final ArrayList<LevelView> levelsViews;
    private int currentLevelViewCount = 0;
    private final Model model;
    private LevelView currentLevelView;

    public GameContent(Model model) {
        this.model = model;
        setFocusable(true);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        knight = new KnightView(model.getKnight());
        knight.setOpaque(false);
        levelsViews = new ArrayList<>();
        initLevelsView();
        currentLevelView = levelsViews.get(currentLevelViewCount);
    }

    private void initLevelsView() {
        ArrayList<Level> modelLevels = model.getLevels();
        for (Level level : modelLevels) {
            ArrayList<Enemy> levelEnemies = level.getEnemies();
            ArrayList<EnemyView> levelEnemiesViews = new ArrayList<>();
            for (Enemy e : levelEnemies) {
                if (e instanceof Golem) {
                    levelEnemiesViews.add(new GolemView((Golem)e));
                }
                else if (e instanceof FallenAngel) {
                    levelEnemiesViews.add(new FallenAngelView((FallenAngel)e));
                }
                else if (e instanceof Reaper) {
                    levelEnemiesViews.add(new ReaperView((Reaper)e));
                }
            }
            levelsViews.add(new LevelView(levelEnemiesViews));
        }
    }

    public KnightView getKnightView() {
        return knight;
    }

    public LevelView getCurrentLevelView() {
        return currentLevelView;
    }

    private void paintMobView(MobView mv, Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (mv.getModel().getDirection() == Direction.RIGHT) {
            g2.drawImage(mv.getNextImageToDraw(), mv.getModel().getPositionX() - mv.getSizeX() / 3,
                    mv.getModel().getPositionY(), mv.getSizeX(), mv.getSizeY(), null);
        }
        else {
            g2.drawImage(mv.getNextImageToDraw(), mv.getModel().getPositionX() + mv.getSizeX() / 3,
                    mv.getModel().getPositionY(), -mv.getSizeX(), mv.getSizeY(), null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (EnemyView ev : currentLevelView.getEnemiesViews()) {
            paintMobView(ev, g2);
        }
        paintMobView(knight, g2);
    }

    public void update() {
        if (currentLevelViewCount != model.getCurrentLevelCount()) {
            currentLevelViewCount = model.getCurrentLevelCount();
            currentLevelView = levelsViews.get(currentLevelViewCount);
        }
        knight.update();
        for (MobView enemy : currentLevelView.getEnemiesViews()) {
            enemy.update();
        }
        repaint();
    }
}
