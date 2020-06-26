package CarFactory.View;

import javax.swing.*;

public class FactoryLabel extends JLabel {
    private final String text;
    public FactoryLabel(String text, int value) {
        this.text = text;
        changeValue(value);
    }
    public void changeValue(int value) {
        setText(text + value);
    }
}
