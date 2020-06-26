package ThreadPool;

public interface Task {
    void doWork() throws InterruptedException;
}
