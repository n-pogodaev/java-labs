package CarFactory.Factory;

import java.util.ArrayList;
import java.util.LinkedList;

public class CarAssembler extends Assembler {
    private final CarFactoryStatistics stat;
    public CarAssembler(LinkedList<Stock> inStocks, Stock outStock, CarFactoryStatistics stat) {
        super(inStocks, outStock);
        this.stat = stat;
    }

    @Override
    protected Car assemble(ArrayList<Part> parts) {
        if (parts.size() < 3) {
            System.err.println("Not enough parts for a car");
            return null;
        }
        Engine engine = null;
        Body body = null;
        Accessory accessory = null;
        Car result;
        for (Part part : parts) {
            if (part instanceof Accessory) {
                accessory = (Accessory)part;
            }
            else if (part instanceof Body) {
                body = (Body)part;
            }
            else if (part instanceof Engine) {
                engine = (Engine)part;
            }
        }
        if (engine == null || body == null || accessory == null) {
            System.err.println("Wrong part type");
            return null;
        }
        stat.decrementAccessoryStockSize();
        stat.decrementBodyStockSize();
        stat.decrementEngineStockSize();
        result = new Car(accessory, body, engine);
        stat.incrementTotalCars();
        return result;
    }

    @Override
    protected void incrementProductStockSize() {
        stat.incrementCarStockSize();
    }
}
