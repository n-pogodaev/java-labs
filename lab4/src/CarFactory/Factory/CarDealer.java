package CarFactory.Factory;

import java.io.PrintStream;
import java.time.LocalTime;

public class CarDealer extends Dealer {
    private final PrintStream logFile;
    private final CarFactoryStatistics stat;
    public CarDealer(Stock stock, int milliseconds, PrintStream logFile, CarFactoryStatistics stat) {
        super(stock, milliseconds);
        this.logFile = logFile;
        this.stat = stat;
    }

    @Override
    protected boolean sellProduct(Product p) {
        if (!(p instanceof Car)) {
            System.err.println("Car dealer got not a car");
            return false;
        }
        Car car = (Car)p;
        if (logFile != null) {
            synchronized (logFile) {
                logFile.println(LocalTime.now().toString() + ": Dealer " + getDealerID() + ": Auto " + car.getID() +
                        " (Body: " + car.getBodyID() + ", Motor: " + car.getEngineID() + ", Accessory: " +
                        car.getAccessoryID() + ")");
            }
        }
        stat.incrementTotalSold();
        return true;
    }

    @Override
    protected void decrementProductStockSize() {
        stat.decrementCarStockSize();
    }
}
