package keDaGongYe.threadDemo.homeWorkTest.fileread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class UsingThreadRandom {
    private static final int DOWN_THREAD_NUM = 30;//起10个线程去读取指定文件
    private static CountDownLatch doneSignal;

    public static void main(String[] args) {
        File file = new File("D:\\绿里奇迹_hd.mp4");
        long start = System.currentTimeMillis() / 1000;
        startThread(DOWN_THREAD_NUM, file.length(), "D:\\绿里奇迹_hd.mp4",
                "D:\\绿里奇迹_copy.mp4");

        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println("读取完毕 耗时： " + (end - start) + "s");
    }


    public static void startThread(int threadnum, long fileLength, String sourseFilePath, String targerFilePath) {
        System.out.println("================");
        System.out.println(fileLength);

        long modLength = fileLength % threadnum;
        System.out.println("modLength:" + modLength);
        long targetLength = fileLength / threadnum;
        System.out.println("targetLength:" + targetLength);
        if (modLength > 0) {
            threadnum++;
        }

        doneSignal = new CountDownLatch(threadnum);

        for (int i = 0; i < threadnum - 1; i++) {
            System.out.println((targetLength * i) + "-----" + (targetLength * (i + 1)));
            new FileWriteThread((targetLength * i), (targetLength * (i + 1)), sourseFilePath, targerFilePath).start();
        }


        if (modLength != 0) {
            System.out.println("最后的文件写入");
            System.out.println((targetLength * threadnum) + "-----" + (targetLength * threadnum + modLength));
            new FileWriteThread((targetLength * threadnum), targetLength * threadnum + modLength, sourseFilePath,
                    targerFilePath).start();
        }
    }

    /**
     * 写线程：指定文件开始位置、目标位置、源文件、目标文件，
     */
    static class FileWriteThread extends Thread {
        private long begin;
        private long end;
        private RandomAccessFile soursefile;
        private RandomAccessFile targerFile;

        public FileWriteThread(long begin, long end, String sourseFilePath, String targerFilePath) {
            this.begin = begin;
            this.end = end;
            try {
                this.soursefile = new RandomAccessFile(sourseFilePath, "rw");
                this.targerFile = new RandomAccessFile(targerFilePath, "rw");
            } catch (FileNotFoundException e) {
            }
        }

        public void run() {
            try {
                soursefile.seek(begin);
                targerFile.seek(begin);
                int hasRead = 0;
                byte[] buffer = new byte[1024 * 1024 * 10];
                while (begin < end && -1 != (hasRead = soursefile.read(buffer))) {
                    // System.out.println("hasRead:"+hasRead);
                    begin += hasRead;
                    targerFile.write(buffer, 0, hasRead);
                }
                doneSignal.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    soursefile.close();
                    targerFile.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
