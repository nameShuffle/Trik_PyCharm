package Async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides methods for asynchronous runs.
 */
public class AsyncExecutor {
    ExecutorService executorService;

    public AsyncExecutor(int numberOfThreads) {
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Runs a runnable task      asynchronously.
     * @param task Runnable task.
     */
    public void execute(Runnable task) {
        executorService.execute(task);
    }
}
