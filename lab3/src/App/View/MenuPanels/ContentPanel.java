package App.View.MenuPanels;

import App.Utils.ViewUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContentPanel extends JPanel {
    private Image background = null;
    private final CardLayout layout = new CardLayout();
    public ContentPanel() {
        try {
            background = ImageIO.read(
                    new File("src\\App\\Resources\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(ViewUtils.MENU_BORDER, ViewUtils.MENU_BORDER,
                ViewUtils.MENU_BORDER, ViewUtils.MENU_BORDER));
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        } catch (NullPointerException ignored){}
    }
}
