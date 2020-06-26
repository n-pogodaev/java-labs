package CarFactory.Model;

import CarFactory.Factory.*;
import CarFactory.Subscriber;
import ThreadPool.ThreadPool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Model implements CarFactoryStatistics {
    public static final int BODY_DEFAULT_MSEC = 200;
    public static final int ENGINE_DEFAULT_MSEC = 200;
    public static final int ACCESSORY_DEFAULT_MSEC = 300;
    public static final int DEALER_DEFAULT_MSEC = 500;
    private int totalAccessories = 0;
    private int accessoryStockSize = 0;
    private int totalBodies = 0;
    private int bodyStockSize = 0;
    private int totalEngines = 0;
    private int engineStockSize = 0;
    private int totalCars = 0;
    private int carStockSize = 0;
    private int currentAssemblingRequests = 0;
    private int totalSold = 0;
    private final List<Subscriber> subscribers = new LinkedList<>();
    private final ArrayList<Thread> threads;
    private final BodySupplier bodySupplier;
    private final EngineSupplier engineSupplier;
    private final ArrayList<AccessorySupplier> accessorySuppliers;
    private final ArrayList<CarDealer> dealers;

    public Model(Properties props) {
        int accessoryStockSize = Integer.parseInt(props.getProperty("AccessoryStockSize", "100"));
        int bodyStockSize = Integer.parseInt(props.getProperty("BodyStockSize", "100"));
        int engineStockSize = Integer.parseInt(props.getProperty("EngineStockSize", "100"));
        int productStockSize = Integer.parseInt(props.getProperty("ProductStockSize", "100"));
        int accessorySuppliersNum = Integer.parseInt(props.getProperty("AccessorySuppliers", "5"));
        int assemblersNum = Integer.parseInt(props.getProperty("Assemblers", "5"));
        int dealersNum = Integer.parseInt(props.getProperty("Dealers", "5"));
        boolean logSales = Boolean.parseBoolean(props.getProperty("LogSales", "true"));

        Stock accessoryStock = new Stock(accessoryStockSize);
        Stock engineStock = new Stock(engineStockSize);
        Stock bodyStock = new Stock(bodyStockSize);
        Object carStockControllerLock = new Object();
        Stock carStock = new Stock(productStockSize, carStockControllerLock);
        ThreadPool assemblersThreadPool = new ThreadPool(assemblersNum);
        LinkedList<Stock> inAssemblingStocks = new LinkedList<>();
        inAssemblingStocks.add(accessoryStock);
        inAssemblingStocks.add(bodyStock);
        inAssemblingStocks.add(engineStock);
        CarAssembler assembler = new CarAssembler(inAssemblingStocks, carStock, this);
        StockController carStockController = new StockController(carStock, assemblersThreadPool, assembler, this,
                carStockControllerLock, dealersNum);
        bodySupplier = new BodySupplier(bodyStock, BODY_DEFAULT_MSEC, this);
        engineSupplier = new EngineSupplier(engineStock, ENGINE_DEFAULT_MSEC, this);
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < accessorySuppliersNum; ++i) {
            accessorySuppliers.add(new AccessorySupplier(accessoryStock, ACCESSORY_DEFAULT_MSEC, this));
        }

        PrintStream logFile = null;
        if (logSales) {
            try {
                logFile = new PrintStream(new FileOutputStream("src\\CarFactory\\sales.log"), true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        dealers = new ArrayList<>();
        for (int i = 0; i < dealersNum; ++i) {
            dealers.add(new CarDealer(carStock, DEALER_DEFAULT_MSEC, logFile, this));
        }
        threads = new ArrayList<>();
        threads.add(new Thread(bodySupplier));
        threads.add(new Thread(engineSupplier));
        for (AccessorySupplier as : accessorySuppliers) {
            threads.add(new Thread(as));
        }
        threads.add(new Thread(carStockController));
        for (Dealer d : dealers) {
            threads.add(new Thread(d));
        }
        PrintStream finalLogFile = logFile;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalLogFile != null) {
                finalLogFile.close();
            }
            for (Thread t: threads) {
                t.interrupt();
            }
        }));
    }

    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    public void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            sub.update();
        }
    }

    public void start() {
        for (Thread t : threads) {
            t.start();
        }
    }

    public void changeAccessorySuppliersSpeed(int milliseconds) {
        for (AccessorySupplier as: accessorySuppliers) {
            as.setSpeed(milliseconds);
        }
    }

    public void changeBodySupplierSpeed(int milliseconds) {
        bodySupplier.setSpeed(milliseconds);
    }

    public void changeEngineSupplierSpeed(int milliseconds) {
        engineSupplier.setSpeed(milliseconds);
    }

    public void changeDealersSpeed(int milliseconds) {
        for (CarDealer d: dealers) {
            d.setSpeed(milliseconds);
        }
    }

    @Override
    public synchronized void incrementTotalAccessories() {
        ++totalAccessories;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementAccessoryStockSize() {
        ++accessoryStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void decrementAccessoryStockSize() {
        --accessoryStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementTotalBodies() {
        ++totalBodies;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementBodyStockSize() {
        ++bodyStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void decrementBodyStockSize() {
        --bodyStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementTotalEngines() {
        ++totalEngines;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementEngineStockSize() {
        ++engineStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void decrementEngineStockSize() {
        --engineStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementTotalCars() {
        ++totalCars;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementCarStockSize() {
        ++carStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void decrementCarStockSize() {
        --carStockSize;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementAssemblingRequests() {
        ++currentAssemblingRequests;
        notifySubscribers();
    }

    @Override
    public synchronized void decrementAssemblingRequests() {
        --currentAssemblingRequests;
        notifySubscribers();
    }

    @Override
    public synchronized void incrementTotalSold() {
        ++totalSold;
        notifySubscribers();
    }

    public int getAccessoryStockSize() {
        return accessoryStockSize;
    }

    public int getBodyStockSize() {
        return bodyStockSize;
    }

    public int getEngineStockSize() {
        return engineStockSize;
    }

    public int getCarStockSize() {
        return carStockSize;
    }

    public int getTotalAccessories() {
        return totalAccessories;
    }

    public int getTotalBodies() {
        return totalBodies;
    }

    public int getTotalCars() {
        return totalCars;
    }

    public int getTotalEngines() {
        return totalEngines;
    }

    public int getCurrentAssemblingRequests() {
        return currentAssemblingRequests;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
