import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private CustomWorker[] workers;
    static final Runnable STOP_SIGNAL = () -> { };
    private volatile boolean isRunning = true;

    public CustomThreadPool(int poolSize) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new CustomWorker[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new CustomWorker(taskQueue, "Worker-" + (i + 1));
            workers[i].start();
        }
    }

    // Submit new task to queue
    public void submit(Runnable task) throws IllegalStateException {
        if (isRunning) {
            taskQueue.offer(task);
        } else {
            throw new IllegalStateException();
        }

    }

    // Gracefully shutdown
    public void shutdown() {
        isRunning = false;
        for (int i = 0; i < workers.length; i++) {
            taskQueue.offer(STOP_SIGNAL);
        }
    }
}
