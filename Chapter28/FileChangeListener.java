package Chapter28;

import java.util.concurrent.*;

public class FileChangeListener {
    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        final EventBus eventBus = new AsyncEventBus(executor);

        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "F:\\fff");
        monitor.startMonitor();
    }
}
