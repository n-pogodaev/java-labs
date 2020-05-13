package App.View.MenuPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LosePanel extends JPanel {
    private final JButton continueButton = new JButton("Continue");
    public LosePanel() {
        setBackground(Color.cyan);
    }

    public void setScore(int score) {

    }

    public void addButtonListener(ActionListener al) {
        continueButton.addActionListener(al);
    }

}
