package ThreadPool;

import java.util.Queue;

public class PooledThread implements Runnable {
    private final Queue<ThreadPoolTask> tasks;
    public PooledThread(Queue<ThreadPoolTask> tasks) {
        this.tasks = tasks;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadPoolTask nextTask;
            synchronized (tasks) {
                if (tasks.isEmpty()) {
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }
                else {
                    nextTask = tasks.remove();
                }
            }
            nextTask.startTask();
            try {
                nextTask.doTask();
            } catch (InterruptedException e) {
                nextTask.interruptTask();
                continue;
            }
            nextTask.finishTask();
        }
    }
}
