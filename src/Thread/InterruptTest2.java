package Thread;

//线程执行中断方法，只是给线程一个中断标记，是否中断是他自己的事
class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("should be stopped and exit");
                    break;
                }
                sleep(1000);
                System.out.println("i=" + (i + 1));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //尽管线程被中断,但并没有结束运行。这行代码还是会被执行
        System.out.println("this line is also executed. thread does not stopped");
    }
}

public class InterruptTest2 {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();//请求中断MyThread线程
        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
