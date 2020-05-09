package App.View;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final int frameDefaultWidth;
    private final int frameDefaultHeight;
    public AppFrame(String title, Image icon, int width, int height) {
        frameDefaultWidth = width;
        frameDefaultHeight = height;
        setSize(frameDefaultWidth, frameDefaultHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(icon);
        setTitle(title);
    }
    public int getWidth() {
        return frameDefaultWidth;
    }
    public int getHeight() {
        return frameDefaultHeight;
    }
}
