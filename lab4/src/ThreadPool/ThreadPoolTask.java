package ThreadPool;

public class ThreadPoolTask {
    private final Task task;
    private final TaskListener taskListener;
    public ThreadPoolTask(Task task, TaskListener taskListener) {
        this.task = task;
        this.taskListener = taskListener;
    }

    public void startTask() {
        taskListener.taskStarted();
    }

    public void finishTask() {
        taskListener.taskFinished();
    }

    public void doTask() throws InterruptedException {
        task.doWork();
    }

    public void interruptTask() {
        taskListener.taskInterrupted();
    }
}
