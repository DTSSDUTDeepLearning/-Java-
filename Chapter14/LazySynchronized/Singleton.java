package Chapter14.LazySynchronized;

public final class Singleton {

    private byte[] data = new byte[1024];

    private static Singleton instance = null;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
