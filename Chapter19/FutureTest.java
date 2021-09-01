package Chapter19;

import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService<String, Integer> service = FutureService.newService();
        service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello", System.out::println);
    }
}
