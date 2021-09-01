package Chapter17;

class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int writingWriters = 0;
    private int waitingWriters = 0;
    private int readingReaders = 0;

    // read和write的偏好设置
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    void incrementWritingWriters() {
        this.writingWriters++;
    }

    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    void incrementReadingReaders() {
        this.readingReaders++;
    }

    void decrementWritingWriters() {
        this.writingWriters--;
    }

    void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    void decrementReadingReaders() {
        this.readingReaders--;
    }

    public int getWritingWriters() {
        return this.writingWriters;
    }

    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    public int getReadingReaders() {
        return this.readingReaders;
    }

    Object getMUTEX() {
        return this.MUTEX;
    }

    // 获取当前是否偏向写锁
    boolean getPreferWriter() {
        return this.preferWriter;
    }

    // 设置写锁偏好
    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
