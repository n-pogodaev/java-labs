package CarFactory.Factory;

public class EngineSupplier extends Supplier {
    private final CarFactoryStatistics stat;
    public EngineSupplier(Stock stock, int milliseconds, CarFactoryStatistics stat) {
        super(stock, milliseconds);
        this.stat = stat;
    }

    @Override
    protected Engine create() {
        stat.incrementTotalEngines();
        return new Engine();
    }

    @Override
    protected void incrementStockSize() {
        stat.incrementEngineStockSize();
    }
}
