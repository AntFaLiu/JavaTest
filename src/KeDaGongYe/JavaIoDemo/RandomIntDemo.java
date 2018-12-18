package KeDaGongYe.JavaIoDemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomIntDemo {
    public static void main(String[] args) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("e:\\demo.txt", "rw");
            for (int i = 0; i < 10; i++) {
                raf.writeInt(i);
            }
            raf.seek(0);
            for(int i=0;i<10;i++){
                System.out.println(raf.readInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
