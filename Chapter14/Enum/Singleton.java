package Chapter14.Enum;

public enum Singleton {
    INSTANCE;

    private byte[] data = new byte[1024];

    Singleton() {
        System.out.println("INSTANCE will br initialized immediately");
    }

    public static void method() {
        // 调用该方法将会主动使用Singleton, INSTANCE将会被初始化
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

}
