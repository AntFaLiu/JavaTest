package lockTest.ReLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁示例
 */
public class FairAndNonFairTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock fairlock = new ReentrantLock(true);
    private int n;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FairAndNonFairTest rlt = new FairAndNonFairTest();
        for (int i = 0; i < 2; i++) {
//            thread nonT = new thread(new NonFairTestThread(rlt));
//            nonT.setName("nonFair[" + (i + 1) + "]");
//            nonT.start();

            Thread fairT = new Thread(new FairTestThread(rlt));
            fairT.setName("fair[" + (i + 1) + "]");
            fairT.start();
        }
    }

    public int getNum() {
        return n;
    }

    public void setNum(int n) {
        this.n = n;
    }

    static class NonFairTestThread implements Runnable {
        private FairAndNonFairTest rlt;

        public NonFairTestThread(FairAndNonFairTest rlt) {
            this.rlt = rlt;
        }

        public void run() {
            while (true) {
                lock.lock();
                try {
                    rlt.setNum(rlt.getNum() + 1);
                    System.out.println(Thread.currentThread().getName()
                            + " nonfairlock***************" + rlt.getNum());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class FairTestThread implements Runnable {
        private FairAndNonFairTest rlt;

        public FairTestThread(FairAndNonFairTest rlt) {
            this.rlt = rlt;
        }

        public void run() {
            while (true) {
                fairlock.lock();
                try {
                    rlt.setNum(rlt.getNum() + 1);
                    System.out.println(Thread.currentThread().getName()
                            + "   fairlock=======" + rlt.getNum() + "   "
                            + fairlock.getHoldCount() + " queuelength="
                            + fairlock.getQueueLength());
                } finally {
                    fairlock.unlock();
                }
            }
        }
    }
}
