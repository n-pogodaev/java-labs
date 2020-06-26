package CarFactory.View;

import javax.swing.*;

public class FactorySlider extends JSlider {
    public FactorySlider(int defaultValue) {
        setMinimum(100);
        setMaximum(1300);
        setValue(defaultValue);
        setMajorTickSpacing(400);
        setMinorTickSpacing(40);
        setPaintTrack(true);
        setPaintTicks(true);
        setPaintLabels(true);
    }
}
