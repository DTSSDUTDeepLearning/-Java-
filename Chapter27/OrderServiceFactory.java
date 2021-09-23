package Chapter27;

import Chapter19.Future;

public final class OrderServiceFactory {

    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    //不允许外部通过new的方式构建
    private OrderServiceFactory() {}

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, activeMessageQueue);
    }

//    public static void main(String[] args) throws InterruptedException {
//        //在创建OrderService时需要传递OrderService接口的具体实现
//        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
//        orderService.order("hello", 453453);
//        System.out.println("Return immediately");
//        Thread.currentThread().join();
//    }

}
