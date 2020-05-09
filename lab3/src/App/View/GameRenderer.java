package App.View;

import App.Model.Model;
import App.Utils.Buttons;
import App.View.Game.GameContent;
import App.View.MenuPanels.*;
import javafx.scene.layout.Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameRenderer implements Renderer {
    private final AppFrame frame;
    private static final String MENU_PANEL = "Menu";
    private static final String HS_PANEL = "High Scores";
    private static final String ABOUT_PANEL = "About";
    private static final String LOSE_PANEL = "Lose";
    private final ContentPanel menuContent;
    private final GameContent gameContent;
    private final MenuPanel menuPanel;
    private final HighScoresPanel hsPanel;
    private final AboutPanel aboutPanel;
    private final LosePanel losePanel;
    private static final int screenWidth;
    private static final int screenHeight;
    private static final int gameLevelGroundHeight;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width * 3 / 5;
        screenHeight = screenSize.height * 3 / 5;
        gameLevelGroundHeight = screenHeight / 3;
    }

    public GameRenderer(Model model) {
        frame = new AppFrame("Forest Knight",
                new ImageIcon("src\\App\\Resources\\icon.png").getImage(), screenWidth, screenHeight);
        menuContent = new ContentPanel();
        menuPanel = new MenuPanel();
        hsPanel = new HighScoresPanel();
        aboutPanel = new AboutPanel();
        losePanel = new LosePanel();
        menuContent.add(menuPanel, MENU_PANEL);
        menuContent.add(hsPanel, HS_PANEL);
        menuContent.add(aboutPanel, ABOUT_PANEL);
        menuContent.add(losePanel, LOSE_PANEL);
        frame.setContentPane(menuContent);
        gameContent = new GameContent(model);
    }

    private void changePanel(String name) {
        menuContent.getLayout().show(menuContent, name);
    }

    @Override
    public void showMenu() {
        changePanel(MENU_PANEL);
    }

    @Override
    public void showHighScores() {
        changePanel(HS_PANEL);
    }

    @Override
    public void showAbout() {
        changePanel(ABOUT_PANEL);
    }

    @Override
    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void showWindow() {
        frame.setVisible(true);
    }

    @Override
    public void addMenuListener(Buttons b, ActionListener al) {
        menuPanel.addButtonListener(b, al);
    }

    @Override
    public void addBackButtonListener(ActionListener al) {
        hsPanel.addButtonListener(al);
        aboutPanel.addButtonListener(al);
        losePanel.addButtonListener(al);
    }

    @Override
    public void showLose(int score) {
        losePanel.setScore(score);
        changePanel(LOSE_PANEL);
        frame.setContentPane(menuContent);
        frame.invalidate();
        frame.repaint();
    }

    @Override
    public void updateHighScores() {
        hsPanel.updateScores();
    }

    @Override
    public void startGame(Model model) {
        JPanel gameBackground = new GameBackground();
        gameBackground.add(gameContent, BorderLayout.CENTER);
        gameBackground.revalidate();
        frame.setContentPane(gameBackground);
        frame.revalidate();
        frame.repaint();
        gameContent.requestFocusInWindow();
    }

    @Override
    public void updateGameView(Model model) {
        gameContent.update(model);
    }

    @Override
    public void addGameListener(KeyListener listener) {
        gameContent.addKeyListener(listener);
    }

    public static int getGameLevelWidth() {
        return screenWidth;
    }

    public static int getGameLevelGroundHeight() {
        return gameLevelGroundHeight;
    }

    public static int getGameLevelHeight() {
        return screenHeight;
    }

    public static class GameBackground extends JPanel {
        private static Image gameBackground = null;
        private static Image ground = null;
        static {
            try {
                gameBackground = ImageIO.read(new File("src\\App\\Resources\\game_background.png"));
                ground = ImageIO.read((new File("src\\App\\Resources\\ForestPlatforms\\Platform_3.png")))
                        .getSubimage(100, 70, 1800, 700);
            } catch (IOException ignored) {}
        }
        public GameBackground() {
            setLayout(new BorderLayout());
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g.drawImage(gameBackground, 0, 0, getWidth(), getHeight(), null);
                g.drawImage(ground, 0, screenHeight - gameLevelGroundHeight * 5 / 6,
                        getWidth(), gameLevelGroundHeight, null);
            } catch (NullPointerException ignored){}
        }
    }
}
