package CarFactory.Factory;

public abstract class Supplier implements Runnable {
    private volatile int milliseconds;
    private final Stock stock;
    protected Supplier(Stock stock, int milliseconds) {
        setSpeed(milliseconds);
        this.stock = stock;
    }

    public void setSpeed(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getSpeed() {
        return milliseconds;
    }

    protected abstract Part create();

    protected abstract void incrementStockSize();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(milliseconds, 0);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted while creating something");
                break;
            }
            Part newCopy = create();
            synchronized (stock) {
                stock.add(newCopy);
                incrementStockSize();
            }
        }
    }
}
