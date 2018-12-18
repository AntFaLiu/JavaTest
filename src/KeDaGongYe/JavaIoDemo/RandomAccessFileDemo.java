package KeDaGongYe.JavaIoDemo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) {
        try {
            boolean b = true;
            // create a new RandomAccessFile with filename test
            RandomAccessFile raf = new RandomAccessFile("E:/test.txt", "rw");

            // write a boolean
            raf.writeBoolean(false);

            // set the file pointer at 0 position
            raf.seek(0);

            // print the boolean
            System.out.println("" + raf.readBoolean());

            // write a boolean
            raf.writeBoolean(b);

            // set the file pointer at position 1
            raf.seek(1);

            // print the boolean
            System.out.println("" + raf.readBoolean());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
