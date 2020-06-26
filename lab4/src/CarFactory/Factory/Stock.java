package CarFactory.Factory;

import java.util.LinkedList;
import java.util.Queue;

public class Stock {
    private final Queue<Product> things = new LinkedList<>();;
    private final int maxSize;
    private final Object controllerLock;

    public Stock(int size) {
        this(size, null);
    }

    public Stock(int size, Object controllerLock) {
        maxSize = size;
        this.controllerLock = controllerLock;
    }

    public synchronized void add(Product p) {
        while (!Thread.currentThread().isInterrupted()) {
            if (things.size() >= maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " has been interrupted in Stock.add()");
                    break;
                }
            } else {
                things.add(p);
                notifyAll();
                break;
            }
        }
    }

    public synchronized Product get() {
        Product result = null;
        while (!Thread.currentThread().isInterrupted()) {
            if (things.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " has been interrupted in Stock.get()");
                    break;
                }
            } else {
                result = things.remove();
                notifyAll();

                if (controllerLock != null) {
                    synchronized (controllerLock) {
                        controllerLock.notifyAll();
                    }
                }
                break;
            }
        }
        return result;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public synchronized int getSize() {
        return things.size();
    }
}
