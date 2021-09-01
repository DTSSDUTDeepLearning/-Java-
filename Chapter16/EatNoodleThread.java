package Chapter16;

import java.util.concurrent.TimeUnit;

public class EatNoodleThread extends Thread {

//    private final String name;
//
//    // 左手边
//    private final Tableware leftTool;
//
//    // 右手边
//    private final Tableware rightTool;
//
//    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
//        this.name = name;
//        this.leftTool = leftTool;
//        this.rightTool = rightTool;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            this.eat();
//        }
//    }
//
//    // 吃面条的过程
//    private void eat() {
//        synchronized (leftTool) {
//            System.out.println(name + " take up " + leftTool + "(left)");
//            synchronized (rightTool) {
//                System.out.println(name + " take up " + rightTool + "(right)");
//                System.out.println(name + " is eating now.");
//                System.out.println(name + " put down " + rightTool + ("right"));
//            }
//            System.out.println(name + " put down " + leftTool + ("left"));
//        }
//    }
//

    private final String name;

    private final TablewarePair tablewarePair;

    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left)");
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        TablewarePair A = new TablewarePair(fork, knife);
        TablewarePair B = new TablewarePair(knife, fork);
        new EatNoodleThread("A", A).start();
        new EatNoodleThread("B", B).start();
    }
}
