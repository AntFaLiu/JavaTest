package keDaGongYe.threadDemo.homeWorkTest.matrix;

public class ThreadOperate extends Thread {
    private OperateMatrix om = null;        //定义矩阵操类对象
    private int line;

    //定义构造方法
    public ThreadOperate() {
        super();
    }

    public ThreadOperate(OperateMatrix om, String name, int line) {
        super(name);        //线程名字
        this.om = om;
        this.line = line;
    }

    //覆写run()方法
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());        //打印当前线程的名字
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * 调用OperateMatrix对象的operate方法，进行矩阵的计算
         * 每次调用只计算一行结果
         * */
        this.om.operate(line);
    }
}
