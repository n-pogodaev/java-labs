package CarFactory.View;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public interface View {
    void start();
    void addChangeListeners(ChangeListener accessorySupplierSpeedListener, ChangeListener bodySupplierSpeedListener,
                      ChangeListener engineSupplierSpeedListener, ChangeListener dealerSpeedListener);
    void addButtonListeners(ActionListener startListener, ActionListener resetListener);
    void reset();
}
