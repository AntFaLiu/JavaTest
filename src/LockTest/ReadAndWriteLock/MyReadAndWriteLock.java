//package lockTest.ReadAndWriteLock;
//
//public class MyReadAndWriteLock {
//    private int writers = 0;
//    private int readers = 0;
//    private int writerequest = 0;
//
//    public synchronized void lockRead() throws InterruptedException {
//        while (writers > 0 || writerequest > 0) {
//            wait();
//        }
//        readers++;
//    }
//
//    public synchronized void unLockRead() {
//        readers--;
//        notifyAll();
//    }
//
//    public synchronized void lockWrite() throws InterruptedException {
//        writerequest++;
//        while (readers > 0 || writers > 0) {
//            wait();
//        }
//        writers++;
//        writerequest--;
//    }
//
//    public synchronized void unLockWrite() {
//        writers--;
//        notifyAll();
//    }
//}

package lockTest.ReadAndWriteLock;

public class MyReadAndWriteLock {
    private int writers = 0;
    private int readers = 0;
    private int writerequest = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writerequest > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unLockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writerequest++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writers++;
        writerequest--;
    }

    public synchronized void unLockWrite() {
        writers--;
        notifyAll();
    }
}
