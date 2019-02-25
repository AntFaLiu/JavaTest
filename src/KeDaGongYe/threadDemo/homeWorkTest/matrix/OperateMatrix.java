package keDaGongYe.threadDemo.homeWorkTest.matrix;

public class OperateMatrix {
    public volatile static int line = 0;    //统计一共计算了多少行  CountDownLatch
    int[][] matrix1 = null;        //第一个矩阵
    int[][] matrix2 = null;        //第二个矩阵
    int[][] result = null;        //存放矩阵相乘结果

    //定义构造方法
    public OperateMatrix() {
    }

    public OperateMatrix(int[][] m1, int[][] m2) {

        this.matrix1 = m1;
        this.matrix2 = m2;
        result = new int[matrix1.length][matrix2[0].length];
    }

    //返回矩阵相乘的结果
    public int[][] getResult() {

        try {
            /*
             * 当矩阵还没有完全计算完时
             * 令当前线程睡眠1毫秒等待
             * 然后再次判断
             * */
            while (OperateMatrix.line < matrix1.length) {
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.result;

    }

    //第一个矩阵的行乘以第二个矩阵的列，得到新矩阵的行
    public void operate(int line) {
        System.out.println("线程：" + Thread.currentThread().getName() + " " + line);
        for (int i = 0; i < matrix1[0].length; i++) {
            int sum = 0;        //存储第一个矩阵的行和 第二个矩阵的列的计算结果
            for (int j = 0; j < matrix2.length; j++) {
                int a = matrix1[line][j];
                int b = matrix2[j][i];
                sum += a * b;        //第一个矩阵的当前行乘以第二个矩阵
            }
            result[line][i] = sum;        //保存结果
        }
        OperateMatrix.line++;    //计算次数加一
    }
}
