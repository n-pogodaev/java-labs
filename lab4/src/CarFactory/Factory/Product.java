package CarFactory.Factory;

public abstract class Product {
    static private int globalNextID = 0;
    private final int ID;
    private final ProductTypes type;
    protected Product(ProductTypes type) {
        ID = globalNextID++;
        this.type = type;
    }

    public ProductTypes getType() {
        return type;
    }

    public int getID() {
        return ID;
    }
}
