package Chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator_changed {
    private int init;

    public IntegerAccumulator_changed(int init) {
        this.init = init;
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator_changed accumulator = new IntegerAccumulator_changed(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
//                int oldValue = accumulator.getValue();
//                int result = accumulator.add(inc);
//                System.out.println(oldValue + "+" + inc + "=" + result);
//                if (inc + oldValue != result) {
//                    System.out.println("ERROR:" + oldValue + "+" + inc + "=" + result);
//                }
//                inc++;
//                slowly();
                int oldValue;
                int result;
                synchronized (IntegerAccumulator_changed.class) {
                    oldValue = accumulator.getValue();
                    result = accumulator.add(inc);
                }
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
