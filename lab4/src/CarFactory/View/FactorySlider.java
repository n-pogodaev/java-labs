package CarFactory.View;

import javax.swing.*;

public class FactorySlider extends JSlider {
    public FactorySlider(int defaultValue) {
        setMinimum(100);
        setMaximum(900);
        setValue(defaultValue);
        setMajorTickSpacing(200);
        setMinorTickSpacing(10);
        setPaintTrack(true);
        setPaintTicks(true);
        setPaintLabels(true);
    }
}
