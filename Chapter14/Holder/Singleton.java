package Chapter14.Holder;

public final class Singleton {

    private byte[] data = new byte[1024];

    private Singleton() {}

    // 在静态内部类中持有Singleton的实例，并且可被直接初始化
    private static class Holder {
        private static Singleton instance = new Singleton();
    }

    // 调用getInstance()方法，实际上是获得Holder的instance静态属性
    public static Singleton getInstance() {
        return Holder.instance;
    }
}
