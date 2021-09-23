package Chapter21;

public class ThreadLocalInitialValueDemo {

    public static void main(String[] args) {
        ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
            @Override
            protected Object initialValue() {
                return new Object();
            }
        };
        new Thread(() ->
                System.out.println(threadLocal.get())
        ).start();
        System.out.println(threadLocal.get());
    }
}
