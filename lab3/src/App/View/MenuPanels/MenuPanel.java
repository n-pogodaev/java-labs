package App.View.MenuPanels;

import App.Utils.Buttons;
import App.Utils.ViewUtils;
import App.View.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private final JButton newGameButton = new JButton("New Game");
    private final JButton highScoresButton = new JButton("High Scores");
    private final JButton aboutButton = new JButton("About");
    private final JButton exitButton = new JButton("Exit");
    private static final String title = "FOREST KNIGHT";
    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        TitlePanel titlePanel = new TitlePanel();
        titlePanel.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 7, GameRenderer.getFrameHeight() / 2));
        titlePanel.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 2, GameRenderer.getFrameHeight() / 5 * 2));
        titlePanel.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 4, GameRenderer.getFrameHeight() / 4));
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getFrameHeight() / 13));
        newGameButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 10, GameRenderer.getFrameHeight() / 26));
        newGameButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 3, GameRenderer.getFrameHeight() / 5));
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        highScoresButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getFrameHeight() / 13));
        highScoresButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 10, GameRenderer.getFrameHeight() / 26));
        highScoresButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 3, GameRenderer.getFrameHeight() / 5));
        highScoresButton.setAlignmentX(CENTER_ALIGNMENT);
        highScoresButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        aboutButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getFrameHeight() / 13));
        aboutButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 10, GameRenderer.getFrameHeight() / 26));
        aboutButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 3, GameRenderer.getFrameHeight() / 5));
        aboutButton.setAlignmentX(CENTER_ALIGNMENT);
        aboutButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        exitButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getFrameHeight() / 13));
        exitButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 10, GameRenderer.getFrameHeight() / 26));
        exitButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 3, GameRenderer.getFrameHeight() / 5));
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(titlePanel);
        add(Box.createVerticalGlue());
        add(newGameButton);
        add(Box.createRigidArea(new Dimension(0, GameRenderer.getFrameHeight() / 27)));
        add(highScoresButton);
        add(Box.createRigidArea(new Dimension(0, GameRenderer.getFrameHeight() / 27)));
        add(aboutButton);
        add(Box.createRigidArea(new Dimension(0, GameRenderer.getFrameHeight() / 27)));
        add(exitButton);
        add(Box.createVerticalGlue());
        //add(Box.createRigidArea(new Dimension(0, 30)));
    }

    public void addButtonListener(Buttons b, ActionListener listener) {
        JButton button;
        switch (b) {
            case NEW_GAME:
                button = newGameButton;
                break;
            case HIGH_SCORES:
                button = highScoresButton;
                break;
            case ABOUT:
                button = aboutButton;
                break;
            case EXIT:
                button = exitButton;
                break;
            default:
                return;
        }
        button.addActionListener(listener);
    }

    static class TitlePanel extends JPanel {
        public TitlePanel() {
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font font = null;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT,
                        new File("src\\App\\Resources\\WarPriestRegular-PanE.ttf"));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            if (font != null) {
                Font sizedFont = font.deriveFont(60f);
                g2.setFont(sizedFont);
                FontRenderContext frc = g2.getFontRenderContext();
                Rectangle2D bounds = g2.getFont().getStringBounds(title, frc);
                int x = (int) (getWidth() - bounds.getWidth()) / 2;
                int y = ViewUtils.MENU_BORDER * 2;
                GlyphVector gv = g2.getFont().createGlyphVector(frc, title);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                g2.setColor(Color.BLACK);;
                g2.drawGlyphVector(gv, x, y);
            }
        }
    }
}
