package Chapter19;

public interface Callback<T> {
    // 任务完成后会调用该方法，其中T为任务执行后的jieg
    void call(T t);
}
