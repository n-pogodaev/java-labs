package CarFactory.Controller;

import CarFactory.Model.Model;
import CarFactory.View.FactorySlider;
import CarFactory.View.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class Controller {
    private final View view;
    private final Model model;
    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.addChangeListeners(new AccessorySupplierSpeedListener(), new BodySupplierSpeedListener(),
                new EngineSupplierSpeedListener(), new DealerSpeedListener());
        ActionListener startListener = actionEvent -> {
            model.start();
        };
        view.addStartListener(startListener);
    }

    public void start() {
        view.start();
    }

    class AccessorySupplierSpeedListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            Object source = changeEvent.getSource();
            if (source instanceof FactorySlider) {
                model.changeAccessorySuppliersSpeed(((FactorySlider)source).getValue());
            }
        }
    }

    class BodySupplierSpeedListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            Object source = changeEvent.getSource();
            if (source instanceof FactorySlider) {
                model.changeBodySupplierSpeed(((FactorySlider)source).getValue());
            }
        }
    }

    class EngineSupplierSpeedListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            Object source = changeEvent.getSource();
            if (source instanceof FactorySlider) {
                model.changeEngineSupplierSpeed(((FactorySlider)source).getValue());
            }
        }
    }

    class DealerSpeedListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            Object source = changeEvent.getSource();
            if (source instanceof FactorySlider) {
                model.changeDealersSpeed(((FactorySlider)source).getValue());
            }
        }
    }
}
