package CarFactory.Factory;

public class AccessorySupplier extends Supplier {
    private final CarFactoryStatistics stat;
    public AccessorySupplier(Stock stock, int milliseconds, CarFactoryStatistics stat) {
        super(stock, milliseconds);
        this.stat = stat;
    }

    @Override
    protected Accessory create() {
        stat.incrementTotalAccessories();
        return new Accessory();
    }

    @Override
    protected void incrementStockSize() {
        stat.incrementAccessoryStockSize();
    }
}
