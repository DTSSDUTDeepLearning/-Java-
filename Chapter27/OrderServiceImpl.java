package Chapter27;

import Chapter19.Future;
import Chapter19.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

    @ActiveMethod
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input ->
        {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("process the orderId -> " + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information";
        }, orderId, null);
    }

    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("process the order for account " + account + ", orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
