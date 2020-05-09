package App.View.MenuPanels;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LosePanel extends JPanel {
    private final JButton continueButton = new JButton("Continue");
    public LosePanel() {


    }

    public void setScore(int score) {

    }

    public void addButtonListener(ActionListener al) {
        continueButton.addActionListener(al);
    }

}
