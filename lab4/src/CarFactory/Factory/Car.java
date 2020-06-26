package CarFactory.Factory;

public class Car extends Product {
    private final Accessory accessory;
    private final Body body;
    private final Engine engine;
    protected Car(Accessory a, Body b, Engine e) {
        super(ProductTypes.CAR);
        accessory = a;
        body = b;
        engine = e;
    }
    public int getAccessoryID() {
        return accessory.getID();
    }
    public int getBodyID() {
        return body.getID();
    }
    public int getEngineID() {
        return engine.getID();
    }
}
