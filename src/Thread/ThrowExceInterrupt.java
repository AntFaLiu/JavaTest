package thread;

class InterruptDemo extends Thread {

    private boolean falg = true;

    public void setFalg() {
        this.falg = false;
    }

    public boolean isFalg() {
        return falg;
    }

    @Override
    public void run() {
        super.run();

        try {
            synchronized (InterruptDemo.class) {
                for (int i = 0; i < 500000; i++) {
//                if (this.interrupted()) {
//                    System.out.println("should be stopped and exit");
//                    throw new InterruptedException();
//                }
                    System.out.println("i=" + (i + 1));
//                sleep(1000);   //线程必须处于等待状态
//                join();
                    InterruptDemo.class.wait();
                }
            }
            System.out.println("this line cannot be executed. cause thread throws exception");//这行语句不会被执行!!!
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程被中断");
        }
    }
}

public class ThrowExceInterrupt {
    public static void main(String[] args) {
        try {
            InterruptDemo thread = new InterruptDemo();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();//请求中断MyThread线程    也可以换成一个状态位
        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
