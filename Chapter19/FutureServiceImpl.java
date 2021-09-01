package Chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作用是当提交任务时，创建一个新的线程来受理该任务，进而达到任务异步执行的效果
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nextCounter = new AtomicInteger(0);
    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行结束之后将null作为结果传给future
            future.finish(null);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束之后，将真实的结果通过finish方法传递给future
            future.finish(result);
        }, getNextName()).start();
        return future;
    }

    // 增加回调接口Callback，当任务执行结束之后，Callback会得到执行
    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            future.finish(result);
            if (callback != null) {
                callback.call(result);
            }
        }, getNextName()).start();
        return future;
    }
}
