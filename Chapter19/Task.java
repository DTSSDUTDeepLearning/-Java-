package Chapter19;

public interface Task<IN, OUT> {
    // 给定一个参数，经过计算，返回结果
    OUT get(IN input);
}
