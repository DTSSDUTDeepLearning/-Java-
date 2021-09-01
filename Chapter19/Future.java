package Chapter19;

public interface Future<T> {

    // 返回计算后的结果
    T get() throws InterruptedException;
    // 判断任务是否已经被执行完毕
    boolean done();
}
