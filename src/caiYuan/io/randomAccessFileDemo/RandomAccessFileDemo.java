package caiYuan.io.randomAccessFileDemo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("file", "rw");
        // 以下向file文件中写数据
        file.writeInt(20);// 占4个字节
        file.writeDouble(8.236598);// 占8个字节
        file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
        file.writeBoolean(true);// 占1个字节
        file.writeShort(395);// 占2个字节
        file.writeLong(2325451l);// 占8个字节
        file.writeFloat(35.5f);// 占4个字节
        file.writeChar('a');// 占2个字节
        file.writeByte('b');
        file.writeChars("di");
        file.writeBytes("lyp");


        file.seek(0);// 把文件指针位置设置到文件起始处

        // 以下从file文件中读数据，要注意文件指针的位置
        System.out.println("——————从file文件指定位置读数据——————");
        System.out.println("file.readInt(): " + file.readInt());
        System.out.println("file.readDouble(): " + file.readDouble());
        System.out.println("file.readUTF(): " + file.readUTF());
        System.out.println("file.readBoolean(): "+file.readBoolean());
        System.out.println("file.readShort(): " + file.readShort());
        System.out.println("file.readLong(): " + file.readLong());
        System.out.println("file.readFloat(): " + file.readFloat());
        System.out.println("file.readChar(): "+file.readChar());
        System.out.println("file.readByte(): "+file.readByte());
        System.out.println("file.readUTF(): "+file.readChar());
        System.out.println("file.readUTF(): "+file.readChar());
        System.out.println(""+file.readByte());
        //以下演示文件复制操作
        System.out.println("——————文件复制（从file到fileCopy）——————");
        file.seek(0);
        RandomAccessFile fileCopy = new RandomAccessFile("fileCopy", "rw");
        int len = (int) file.length();//取得文件长度（字节数）
        byte[] b = new byte[len];
        file.readFully(b);
        fileCopy.write(b);
        System.out.println("复制完成！");
    }
}
