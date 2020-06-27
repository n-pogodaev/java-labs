package CarFactory.View;

import CarFactory.Model.Model;
import CarFactory.Subscriber;

import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI implements View, Subscriber {
    private final MainFrame frame = new MainFrame(800, 600);
    private final Model model;
    public GUI(Model model) {
        this.model = model;
        model.addSubscriber(this);
    }

    @Override
    public void start() {
        frame.setVisible(true);
    }

    @Override
    public void addChangeListeners(ChangeListener accessorySupplierSpeedListener,
                                   ChangeListener bodySupplierSpeedListener, ChangeListener engineSupplierSpeedListener,
                                   ChangeListener dealerSpeedListener) {
        frame.addAccessorySupplierSpeedListener(accessorySupplierSpeedListener);
        frame.addBodySupplierSpeedListener(bodySupplierSpeedListener);
        frame.addEngineSupplierSpeedListener(engineSupplierSpeedListener);
        frame.addDealerSpeedListener(dealerSpeedListener);
    }

    @Override
    public void addButtonListeners(ActionListener startListener, ActionListener resetListener) {
        frame.addStartButtonListener(startListener);
        frame.addResetButtonListener(resetListener);
    }

    @Override
    public void reset() {
        EventQueue.invokeLater(() -> frame.update(0,0,0,0,0,0,0,0,0,0));
    }

    @Override
    public void update() {
        EventQueue.invokeLater(() -> frame.update(model.getAccessoryStockSize(),model.getTotalAccessories(),
                model.getBodyStockSize(), model.getTotalBodies(), model.getEngineStockSize(),
                model.getTotalEngines(),model.getCarStockSize(), model.getTotalCars(),
                model.getCurrentAssemblingRequests(), model.getTotalSold()));
    }


}
