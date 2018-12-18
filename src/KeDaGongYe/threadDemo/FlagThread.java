package KeDaGongYe.threadDemo;

public class FlagThread extends Thread {
    //设置中断标志
    private boolean isInterrupted = false;

    public static void main(String[] args) {
        Thread thread = new FlagThread();
        thread.start();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((FlagThread) thread).stopCurrentThread();
    }

    //中断线程方法
    public void stopCurrentThread() {
        this.isInterrupted = true; //中断标志
    }

    @Override
    public void run() {
        while (!isInterrupted) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(this.getName() + ":" + i);
                }
        }
        System.out.println("异常结束！");
    }
}

