package Chapter14.DoubleCheckVolatile;

import java.net.Socket;
import java.sql.Connection;

public final class Singleton {

    private byte[] data = new byte[1024];

    // 加volatile关键字保证有序性，防止指令重排序
    private volatile static Singleton instance = null;

    Connection conn;

    Socket socket;

    private Singleton() {
        // 先实例化conn和socket等实例变量
        // 最后实例化instance
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
