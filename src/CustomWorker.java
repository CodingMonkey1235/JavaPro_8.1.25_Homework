import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class CustomWorker extends Thread {
    private BlockingQueue<Runnable> taskQueue;
    private volatile boolean isRunning = true;

    public CustomWorker(BlockingQueue<Runnable> queue, String name) {
        super(name);
        this.taskQueue = queue;
    }

    public void run() {
        try {
            while (isRunning) {
                Runnable task = taskQueue.take();
                if (task == CustomThreadPool.STOP_SIGNAL) {
                    break;
                }
                task.run();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        isRunning = false;
        this.interrupt();
    }
}
