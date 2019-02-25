package keDaGongYe.threadDemo.homeWorkTest.matrix;

public class TestDemo {
    public static void main(String args[]) {
        //定义两个矩阵
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};   //3*3
        int[][] m2 = {{1, 2, 1}, {1, 1, 2}, {2, 1, 1}};   //3*3
//        ExecutorService executorFixed = Executors.newFixedThreadPool(3);
        OperateMatrix om = new OperateMatrix(m1, m2);  //实例化OperateMatrix对象

        //根据第一个矩阵的行数，启动对应数量的线程
        for (int i = 0; i < m1.length; i++) {
            new Thread(new ThreadOperate(om, "计算第一个矩阵的第" + (i + 1)
                    + "行*第二个矩阵的所有列", i)).start();
//            executorFixed.execute(
//                    new ThreadOperate(om, "计算第一个矩阵的第" + (i + 1)
//                            + "行*第二个矩阵的所有列", i)
//            );
        }
        display(om.getResult());    //打印结果
       // executorFixed.shutdown();
    }


    public static void display(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
    }

}

