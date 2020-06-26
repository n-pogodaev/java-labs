package ThreadPool;

public interface TaskListener {
    void taskStarted();
    void taskFinished();
    void taskInterrupted();
}
