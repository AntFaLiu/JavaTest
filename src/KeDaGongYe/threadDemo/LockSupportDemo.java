package keDaGongYe.threadDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    private volatile boolean blocked = false;
    private Thread current;

    public LockSupportDemo() {
        super();
        this.current = Thread.currentThread();
    }

    public Thread getTargetThread() {
        return this.current;
    }

    public void block() {
        doBlock(false ,0L);
    }

    public void block(long timeout, TimeUnit unit) {
        doBlock(true, unit.toNanos(timeout));
    }

    private void doBlock(boolean timed, long nanos) {
        if(current != Thread.currentThread()) {
            throw new RuntimeException();
        }

        final long deadline = timed ? System.nanoTime() + nanos : 0L;
        blocked = true;
        while(blocked) {
            if(timed) {//如果设置了超时时间，则需要判断是否超时
                nanos = deadline - System.nanoTime();
                if (nanos > 0L) {
                    //还没超时，继续阻塞
                    LockSupport.parkNanos(this, nanos);
                }else{
                    blocked = false;
                }
            }else{
                LockSupport.park(this);
            }

            if(current.isInterrupted()) {
                blocked = false;
            }
        }
    }


    public void unblock() {
        if(blocked) {
            blocked = false;
            LockSupport.unpark(current);
        }
    }

    public static void main(String[] args) {
        final LockSupportDemo bt = new LockSupportDemo();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bt.unblock();
                System.out.println("unblock");

                //中断也可以
                //bt.getTargetThread().interrupt();
                //System.out.println("interrupted");
            }
        }.start();

        System.out.println("block");
        bt.block();

        System.out.println("complete");
    }
}
