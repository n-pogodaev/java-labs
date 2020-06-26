package CarFactory.Factory;

public class BodySupplier extends Supplier {
    private final CarFactoryStatistics stat;
    public BodySupplier(Stock stock, int milliseconds, CarFactoryStatistics stat) {
        super(stock, milliseconds);
        this.stat = stat;
    }

    @Override
    protected Body create() {
        stat.incrementTotalBodies();
        return new Body();
    }

    @Override
    protected void incrementStockSize() {
        stat.incrementBodyStockSize();
    }
}
