package App.View.MenuPanels;

import App.Utils.HighScore;
import App.Utils.ViewUtils;
import App.View.GameRenderer;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class HighScoresPanel extends JPanel {
    private final JButton backButton = new JButton("Back");
    private final int[] highScores;
    public HighScoresPanel() {
        highScores = HighScore.getScore();
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TextPanel text = new TextPanel();
        text.setAlignmentX(CENTER_ALIGNMENT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        backButton.setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 10, GameRenderer.getFrameHeight() / 18));
        backButton.setMinimumSize(new Dimension(GameRenderer.getFrameWidth() / 14, GameRenderer.getFrameHeight() / 35));
        backButton.setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 7, GameRenderer.getFrameHeight() / 11));
        backButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttonPanel.add(backButton);
        add(Box.createVerticalGlue());
        add(text);
        add(Box.createVerticalGlue());
        add(buttonPanel);
    }

    public void updateScores() {
        HighScore.updateScore(highScores);
    }

    public void addButtonListener(ActionListener al) {
        backButton.addActionListener(al);
    }

    class TextPanel extends JPanel {
        public TextPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            setBackground(Color.green.darker().darker());
            setMinimumSize(new Dimension(GameRenderer.getFrameWidth()/ 7, GameRenderer.getFrameHeight() / 20 * 13));
            setPreferredSize(new Dimension(GameRenderer.getFrameWidth() / 5, GameRenderer.getFrameHeight() / 20 * 13));
            setMaximumSize(new Dimension(GameRenderer.getFrameWidth() / 10 * 3, GameRenderer.getFrameHeight() / 20 * 13));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.black);
            Font font = new Font("SansSerif", Font.BOLD, 20);
            g2.setFont(font);
            FontRenderContext frc = g2.getFontRenderContext();
            String[] strScores = new String[highScores.length];
            for (int i = 0; i < highScores.length; ++i) {
                strScores[i] = (i + 1) + ". " + highScores[i];
            }
            Rectangle2D bounds = font.getStringBounds(strScores[0], frc);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            for (int i = 0; i < strScores.length; ++i) {
                int x = (int)(getWidth() - bounds.getWidth()) / 4;
                int y = (int)(i * (bounds.getHeight() + 10) + ViewUtils.MENU_BORDER / 4 * 3);
                g2.drawString(strScores[i], x, y);
            }
        }
    }
}
