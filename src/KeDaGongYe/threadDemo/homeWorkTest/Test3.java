package keDaGongYe.threadDemo.homeWorkTest;

/**
 * 设计4个线程，其中两个线程每次对j增加1，另外2个线程每次对j减少1.写出程序
 */

public class Test3 {
    private int j;

    /**
     * @param args
     */
    public static void main(String[] args) {
        Test3 t = new Test3();
        for (int i = 0; i < 2; i++) {
            Runnable at = t.new Add();
            Runnable st = t.new Sub();
            new Thread(at, "Add-" + i).start();
            new Thread(st, "Sub-" + i).start();
        }
    }

    private synchronized void add() {
        System.out.println(Thread.currentThread().getName() + ",j=" + ++j);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private synchronized void sub() {
        System.out.println(Thread.currentThread().getName() + ",j=" + --j);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private class Add implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                add();
            }
//            while (true) {
//                add();
//            }
        }
    }

    private class Sub implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                sub();
            }
//            while (true) {
//                sub();
//            }
        }
    }

}
