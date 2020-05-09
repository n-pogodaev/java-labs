package App.View.MenuPanels;

import App.Utils.Buttons;
import App.Utils.ViewUtils;

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
        titlePanel.setMaximumSize(new Dimension(700, 250));
        titlePanel.setPreferredSize(new Dimension(500, 200));
        titlePanel.setMinimumSize(new Dimension(400, 150));
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setPreferredSize(new Dimension(200, 40));
        newGameButton.setMinimumSize(new Dimension(100, 20));
        newGameButton.setMaximumSize(new Dimension(300, 100));
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        highScoresButton.setPreferredSize(new Dimension(200, 40));
        highScoresButton.setMinimumSize(new Dimension(100, 20));
        highScoresButton.setMaximumSize(new Dimension(300, 100));
        highScoresButton.setAlignmentX(CENTER_ALIGNMENT);
        highScoresButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        aboutButton.setPreferredSize(new Dimension(200, 40));
        aboutButton.setMinimumSize(new Dimension(100, 20));
        aboutButton.setMaximumSize(new Dimension(300, 100));
        aboutButton.setAlignmentX(CENTER_ALIGNMENT);
        aboutButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        exitButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setMinimumSize(new Dimension(100, 20));
        exitButton.setMaximumSize(new Dimension(300, 100));
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(titlePanel);
        add(Box.createVerticalGlue());
        add(newGameButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(highScoresButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(aboutButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
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
