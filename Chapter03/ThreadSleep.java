package Chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void main(String[] args) {
        new Thread(() ->
        {
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTime = System.currentTimeMillis();
            System.out.printf("Total spend %d ms%n", (endTime-startTime));
        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.printf("Main thread total spend %d ms", (endTime-startTime));
    }

    private static void sleep(long ms) {
        try {
//            Thread.sleep(ms);
//            TimeUnit.HOURS.sleep(3);
//            TimeUnit.MINUTES.sleep(24);
            TimeUnit.SECONDS.sleep(7);
            TimeUnit.MILLISECONDS.sleep(88);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
