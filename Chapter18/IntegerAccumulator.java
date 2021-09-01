package Chapter18;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// 不可变类，不允许被继承
public final class IntegerAccumulator {

    private final int init;

    // 构造时传入初始值
    public IntegerAccumulator(int init) {
        this.init = init;
    }

    // 构造新的累加器，需要用到另外一个accumulator和初始值
    public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }

    // 每次相加都会产生一个新的IntegerAccumulator
    public IntegerAccumulator add(int i) {
        return new IntegerAccumulator(this, i);
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
                System.out.println(oldValue + "+" + inc + "=" + result);
                if (inc + oldValue != result) {
                    System.out.println("ERROR:" + oldValue + "+" + inc + "=" + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
