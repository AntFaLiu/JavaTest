package Thread;

/**
 * 原因在于，自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致下面这种情况出现：
 * 线程1对变量进行自增操作：线程1先读取变量inc的原始值，然后线程1被阻塞了（还没有 inc 的值）；
 * 然后线程2对变量进行自增操作：线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。
 * 然后线程1接着进行加1操作，由于已经读取了inc的值，此时线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入工作内存，最后写入主存。
 */
public class Volatile {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Volatile test = new Volatile();
        for (int i = 0; i < 100; i++) {
            new Thread() {

                public void run() {
                    super.run();
                    test.increase();
                }
            }.start();
        }

//        while (Thread.activeCount() > 1) {
//            //保证前面的线程都执行完
//            System.out.println("Thread.activeCount" + Thread.activeCount());
//            Thread.yield();
//        }

        System.out.println(test.inc);
    }
}
