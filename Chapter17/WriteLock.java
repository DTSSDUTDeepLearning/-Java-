package Chapter17;

class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                // 首先使等待获取写锁的线程数增加
                readWriteLock.incrementWaitingWriters();
                // 如果有其他线程在进行读或者写，则当前线程挂起
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                // 获取到了写锁，则等待写锁的线程数减少
                this.readWriteLock.decrementWaitingWriters();
            }
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            // 释放锁，正在写锁线程数量减少
            readWriteLock.decrementWritingWriters();
            // 更改偏向，使得读锁线程获得更多的机会
            readWriteLock.changePrefer(false);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
