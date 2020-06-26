package CarFactory.Factory;

public abstract class Dealer implements Runnable {
    private static int globalDealerID = 0;
    private final int dealerID;
    private volatile int milliseconds;
    private final Stock stock;
    public Dealer(Stock stock, int milliseconds) {
        this.stock = stock;
        this.milliseconds = milliseconds;
        dealerID = globalDealerID++;
    }

    protected abstract boolean sellProduct(Product p);

    protected abstract void decrementProductStockSize();

    public void setSpeed(int milliseconds) {
        if (milliseconds > 0) {
            this.milliseconds = milliseconds;
        }
    }

    public int getSpeed() {
        return milliseconds;
    }

    public int getDealerID() {
        return dealerID;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(milliseconds, 0);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted while dealing pause");
                break;
            }
            Product p = stock.get();
            if (!sellProduct(p)) {
                stock.add(p);
                return;
            }
            decrementProductStockSize();
        }
    }
}
