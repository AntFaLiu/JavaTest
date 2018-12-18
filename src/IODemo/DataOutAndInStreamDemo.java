package IODemo;

import java.io.*;

public class DataOutAndInStreamDemo {
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\java.txt"));
        dos.writeUTF("流");
        dos.writeInt(1234567);
        dos.writeBoolean(true);
        dos.writeShort((short) 123);
        dos.writeLong((long) 456);
        dos.writeDouble(99.98);
        DataInputStream dis = new DataInputStream(new FileInputStream("D:\\java.txt"));
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readShort());
        System.out.println(dis.readLong());
        System.out.println(dis.readDouble());
        System.out.println(dos.size());
        dis.close();
        dos.close();
    }
}
