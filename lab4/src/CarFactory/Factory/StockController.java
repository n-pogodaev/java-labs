package CarFactory.Factory;

import ThreadPool.ThreadPool;
import ThreadPool.TaskListener;

public class StockController implements Runnable, TaskListener {
    private final Stock stock;
    private final ThreadPool assemblersThreadPool;
    private final Assembler assembler;
    private final CarFactoryStatistics stat;
    private final Object controllerLock;
    private final int dealersNum;

    public StockController(Stock stock, ThreadPool assemblersThreadPool, Assembler assembler,
                           CarFactoryStatistics stat, Object controllerLock, int dealersNum) {
        this.stock = stock;
        this.assemblersThreadPool = assemblersThreadPool;
        this.assembler = assembler;
        this.stat = stat;
        this.controllerLock = controllerLock;
        this.dealersNum = dealersNum;
    }

    private boolean waitStock() {
        synchronized (controllerLock) {
            try {
                controllerLock.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " (stock controller) has been interrupted in waiting");
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int maxSize = stock.getMaxSize();
            int stockSize = stock.getSize();
            if (stockSize > maxSize / 2) {
                waitStock();
                continue;
            }
            for (int i = 0; i < 2 * dealersNum; ++i) {
                if (assemblersThreadPool.addTask(assembler, this)) {
                    stat.incrementAssemblingRequests();
                }
            }
            waitStock();
        }
    }

    @Override
    public void taskStarted() {
        stat.decrementAssemblingRequests();
    }

    @Override
    public void taskFinished() {}

    @Override
    public void taskInterrupted() {}
}
