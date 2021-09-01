package Chapter14.Lazy;

public final class Singleton {

    private byte[] data = new byte[1024];

    private static Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
