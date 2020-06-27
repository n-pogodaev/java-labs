package ThreadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<ThreadPoolTask> tasks = new LinkedList<>();
    private static final int MAX_TASKS = 50;
    private final int maxThreads;
    public ThreadPool(int threadsNum) {
        maxThreads = threadsNum;
    }

    public boolean addTask(Task task, TaskListener taskListener) {
        ThreadPoolTask newTask = new ThreadPoolTask(task, taskListener);
        boolean isTaskAddedToQueue = false;
        synchronized (tasks) {
            if (tasks.size() == threads.size() && threads.size() < maxThreads) {
                Thread newThread = new Thread(new PooledThread(tasks));
                threads.add(newThread);
                newThread.start();
            }
            if (tasks.size() < MAX_TASKS) {
                tasks.add(newTask);
                tasks.notifyAll();
                isTaskAddedToQueue = true;
            }
        }
        return isTaskAddedToQueue;
    }

    public void interrupt() {
        for (Thread t : threads) {
            t.interrupt();
        }
        threads.clear();
        tasks.clear();
    }
}
