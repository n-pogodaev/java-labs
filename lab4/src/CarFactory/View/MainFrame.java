package CarFactory.View;

import CarFactory.Model.Model;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final FactorySlider accessorySupplierSpeed = new FactorySlider(Model.ACCESSORY_DEFAULT_MSEC);
    private final FactorySlider bodySupplierSpeed = new FactorySlider(Model.BODY_DEFAULT_MSEC);
    private final FactorySlider engineSupplierSpeed = new FactorySlider(Model.ENGINE_DEFAULT_MSEC);
    private final FactorySlider dealerSpeed = new FactorySlider(Model.DEALER_DEFAULT_MSEC);

    private final FactoryLabel accessoryStockSize = new FactoryLabel("Accessories in stock: ", 0);
    private final FactoryLabel totalAccessoriesSupplied = new FactoryLabel("Total accessories supplied: ", 0);
    private final FactoryLabel bodyStockSize = new FactoryLabel("Bodies in stock: ", 0);
    private final FactoryLabel totalBodiesSupplied = new FactoryLabel("Total bodies supplied: ", 0);
    private final FactoryLabel engineStockSize = new FactoryLabel("Engines in stock: ", 0);
    private final FactoryLabel totalEnginesSupplied = new FactoryLabel("Total engines supplied: ", 0);
    private final FactoryLabel carStockSize = new FactoryLabel("Cars in stock: ", 0);
    private final FactoryLabel totalCarsAssembled = new FactoryLabel("Total cars assembled: ", 0);
    private final FactoryLabel currentAssemblingRequests = new FactoryLabel("Car requests in queue: ", 0);
    private final FactoryLabel totalSold = new FactoryLabel("Total cars sold: ", 0);

    private final JButton startButton = new JButton("Start");

    private final JPanel mainPanel = new JPanel();

    public MainFrame(int width, int height) {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(new JLabel("Accessory supplier speed (ms delay)"));
        mainPanel.add(accessorySupplierSpeed);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        mainPanel.add(new JLabel("Body supplier speed (ms delay)"));
        mainPanel.add(bodySupplierSpeed);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        mainPanel.add(new JLabel("Engine supplier speed (ms delay)"));
        mainPanel.add(engineSupplierSpeed);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        mainPanel.add(new JLabel("Dealer speed (ms delay)"));
        mainPanel.add(dealerSpeed);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 20)));

        mainPanel.add(accessoryStockSize);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(totalAccessoriesSupplied);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(bodyStockSize);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(totalBodiesSupplied);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(engineStockSize);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(totalEnginesSupplied);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(carStockSize);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(totalCarsAssembled);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(currentAssemblingRequests);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 5)));
        mainPanel.add(totalSold);
        mainPanel.add(Box.createRigidArea(new Dimension(1, 10)));

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);

        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        invalidate();
        repaint();
    }

    public void addAccessorySupplierSpeedListener(ChangeListener cl) {
        accessorySupplierSpeed.addChangeListener(cl);
    }

    public void addBodySupplierSpeedListener(ChangeListener cl) {
        bodySupplierSpeed.addChangeListener(cl);
    }

    public void addEngineSupplierSpeedListener(ChangeListener cl) {
        engineSupplierSpeed.addChangeListener(cl);
    }

    public void addDealerSpeedListener(ChangeListener cl) {
        dealerSpeed.addChangeListener(cl);
    }

    public void addStartButtonListener(ActionListener al) {
        startButton.addActionListener(al);
    }

    public void update(int accessoryStockSize, int totalAccessoriesSupplied, int bodyStockSize, int totalBodiesSupplied,
                       int engineStockSize, int totalEnginesSupplied, int carStockSize, int totalCarsAssembled,
                       int currentAssemblingRequests, int totalSold) {
        this.accessoryStockSize.changeValue(accessoryStockSize);
        this.totalAccessoriesSupplied.changeValue(totalAccessoriesSupplied);
        this.bodyStockSize.changeValue(bodyStockSize);
        this.totalBodiesSupplied.changeValue(totalBodiesSupplied);
        this.engineStockSize.changeValue(engineStockSize);
        this.totalEnginesSupplied.changeValue(totalEnginesSupplied);
        this.carStockSize.changeValue(carStockSize);
        this.totalCarsAssembled.changeValue(totalCarsAssembled);
        this.currentAssemblingRequests.changeValue(currentAssemblingRequests);
        this.totalSold.changeValue(totalSold);
        mainPanel.validate();
        mainPanel.repaint();
    }

}
