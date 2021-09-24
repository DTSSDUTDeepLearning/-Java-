package Chapter29;

import java.util.concurrent.*;

public abstract class AsyncChannel implements Channel<Event> {

    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2));
    }

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    protected abstract void handle(Event message);

    void stop() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
