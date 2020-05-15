package App.View.MenuPanels;

import App.Utils.ViewUtils;
import App.View.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LosePanel extends JPanel {
    private static final String gameOverText = "GAME OVER";
    private int score = 0;
    private final JButton continueButton = new JButton("Continue");
    public LosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        setBackground(Color.green.darker().darker());
        setMaximumSize(new Dimension(GameRenderer.getFrameWidth() * 53 / 100, GameRenderer.getGameLevelHeight() * 2 / 3));
        setPreferredSize(new Dimension(GameRenderer.getFrameWidth() * 53 / 100, GameRenderer.getGameLevelHeight() * 2 / 3));
        setMinimumSize(new Dimension(GameRenderer.getFrameWidth() * 53 / 100, GameRenderer.getGameLevelHeight() * 2 / 3));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        continueButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 7, GameRenderer.getGameLevelHeight() / 18));
        continueButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 9, GameRenderer.getGameLevelHeight() / 35));
        continueButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getGameLevelHeight() / 10));
        continueButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttonPanel.add(continueButton);
        add(Box.createVerticalGlue());
        add(buttonPanel);
        add(Box.createRigidArea(new Dimension(0, GameRenderer.getFrameHeight() / 13)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setColor(Color.black);
        Font font1 = new Font("SansSerif", Font.BOLD, GameRenderer.getFrameWidth() * GameRenderer.getFrameHeight() / 9331);
        g2.setFont(font1);
        int x = GameRenderer.getFrameWidth() * 23 / 100;
        int y = GameRenderer.getFrameHeight() / 4;
        g2.drawString(gameOverText, x, y);
        x = GameRenderer.getFrameWidth() * 32 / 100;
        y = GameRenderer.getFrameHeight() * 2 / 5;
        Font font2 = new Font("SansSerif", Font.BOLD, GameRenderer.getFrameWidth() * GameRenderer.getFrameHeight() / 14930);
        g2.setFont(font2);
        g2.drawString("SCORE: " + score, x, y);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addButtonListener(ActionListener al) {
        continueButton.addActionListener(al);
    }
}
