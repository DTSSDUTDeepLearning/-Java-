package Chapter28;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleSubscriber2 {
    @Subscribe
    public void method1(String message) {
        System.out.println("==SimpleSubscriber1==method1==" + message);
    }

    @Subscribe(topic = "test")
    public void method2(String message) {
        System.out.println("==SimpleSubscriber1==method2==" + message);
    }

    //异步bus
    public static void main(String[] args) {
        Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        bus.register(new SimpleSubscriber2());
        bus.register(new SimpleSubscriber2());
        bus.post("Hello");
        System.out.println("----------");
        bus.post("Hello", "test");
    }
}
