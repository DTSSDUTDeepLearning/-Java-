package Chapter17;

public interface ReadWriteLock {
    // 创建读锁
    Lock readLock();

    // 创建写锁
    Lock writeLock();

    // 获取当前有多少线程正在执行写操作
    int getWritingWriters();

    // 获取当前有多少线程正在等待获取写锁
    int getWaitingWriters();

    // 获取当前有多少线程正在等待获取读锁
    int getReadingReaders();

    // 工厂方法，创建ReadWriteLock
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    // 工厂方法，传入preferWriter，并创建ReadWriteLock
    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
