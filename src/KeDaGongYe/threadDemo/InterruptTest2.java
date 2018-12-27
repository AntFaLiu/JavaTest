package KeDaGongYe.threadDemo;


class MyThreadDemo extends Thread {
    private Object obj = new Object();

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                this.join();
//                    MyThreadDemo.class.wait();
                System.out.println("i=" + (i + 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
//            return;
        }

        //尽管线程被中断,但并没有结束运行。这行代码还是会被执行
        System.out.println("this line is also executed. thread does not stopped");
    }
}

public class InterruptTest2 {
    public static void main(String[] args) {
        try {
            MyThreadDemo thread = new MyThreadDemo();
            thread.start();
            thread.interrupt();
        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
