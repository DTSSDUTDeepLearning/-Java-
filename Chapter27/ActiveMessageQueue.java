package Chapter27;

import java.util.LinkedList;

public class ActiveMessageQueue {

//    //用于存放提交的MethodMessage消息
//    private final LinkedList<MethodMessage> messages = new LinkedList<>();
//
//    public ActiveMessageQueue() {
//        //启动Worker线程
//        new ActiveDaemonThread(this).start();
//    }
//
//    public void offer(MethodMessage methodMessage) {
//        synchronized (this) {
//            messages.addLast(methodMessage);
//            //因为只有一个线程负责take线程，因此没有必要使用notifyAll方法
//            this.notify();
//        }
//    }
//
//    protected MethodMessage take() {
//        synchronized (this) {
//            //当MethodMessage队列中没有Message的时候，执行线程进入阻塞
//            while (messages.isEmpty()) {
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return messages.removeFirst();
//        }
//    }

    //通用ActiveMessageQueue
    private final LinkedList<ActiveMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage activeMessage) {
        synchronized (this) {
            messages.addLast(activeMessage);
            this.notify();
        }
    }

    public ActiveMessage take() {
        synchronized (this) {
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst();
        }
    }
}

class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

//    @Override
//    public void run() {
//        while (true) {
//            //从MethodMessage队列中获取一个MethodMessage，然后执行execute方法
//            MethodMessage methodMessage = this.queue.take();
//            methodMessage.execute();
//        }
//    }


    @Override
    public void run() {
        while (true) {
            ActiveMessage activeMessage = this.queue.take();
            activeMessage.execute();
        }
    }
}