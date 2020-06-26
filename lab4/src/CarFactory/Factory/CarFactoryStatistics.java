package CarFactory.Factory;

public interface CarFactoryStatistics {
    void incrementTotalAccessories();
    void incrementAccessoryStockSize();
    void decrementAccessoryStockSize();
    void incrementTotalBodies();
    void incrementBodyStockSize();
    void decrementBodyStockSize();
    void incrementTotalEngines();
    void incrementEngineStockSize();
    void decrementEngineStockSize();
    void incrementTotalCars();
    void incrementCarStockSize();
    void decrementCarStockSize();
    void incrementAssemblingRequests();
    void decrementAssemblingRequests();
    void incrementTotalSold();
}
