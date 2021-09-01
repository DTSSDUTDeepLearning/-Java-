package Chapter14.Hungry;

// 饿汉式的单例模式的设计
// final不允许被继承
public final class Singleton {

    // 实例变量
    private byte[] data = new byte[1024];

    // 在定义实例对象的时候直接初始化
    private static Singleton instance = new Singleton();

    // 私有构造函数，不允许外部new
    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}