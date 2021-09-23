package Chapter24;

import java.util.concurrent.ThreadPoolExecutor;

public class Operator {

    public void call(String business) {
        //为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        new Thread(taskHandler).start();
    }

    //可以使用线程池替代，为每一个请求创建线程
}
