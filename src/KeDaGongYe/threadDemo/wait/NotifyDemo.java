package keDaGongYe.threadDemo.wait;
//要打印1——100，三个线程打印，
//        线程1打印1，2，3，4，5
//        线程2打印6，7，8，9，10
//        线程3打印11，12，13，14，15
//        线程1打印16，17，18，19，20
//        线程2打印21。。。
//        线程3打印26。。
class WaitDemo extends Thread {
    private Value  value;
    private  int index;

    public WaitDemo(Value value, int index) {
        this.index = index;
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (value) {
                while (value.getIndex() != index) {
                    try {
                        value.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //判断是否结束，到达100之后就结束
                if (value.getNum() > 100){
                    value.notifyAll();
                    return;
                }

                int v = value.getNum();
                System.out.println(Thread.currentThread().getName()+":"+v+","+(v+1)+","+(v+2)+","+(v+3)+","+(v+4));
                value.setNum(v+5);
                value.setIndex((index+1)%3);
                value.notifyAll();
            }
        }
    }
}

class Value {
    private volatile int num;
    private int index;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

public class NotifyDemo {
    public static void main(String[] args) {
        Value value = new Value();
        value.setIndex(0);
        value.setNum(1);
        for (int i = 0; i < 3; i++) {
            new WaitDemo(value, i).start();
        }
    }
}
