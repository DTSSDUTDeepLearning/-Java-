package Chapter14.DoubleCheck;

import java.net.Socket;
import java.sql.Connection;

public final class Singleton {

    private byte[] data = new byte[1024];

    private static Singleton instance = null;

    // conn和socket模拟其他需要初始化的实例变量
    Connection conn;
    Socket socket;

    private Singleton() {
        // 此处需要实例化conn和socket等实例变量
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
