package caiYuan.io;

import java.io.*;
//ObjectInputStream与ObjectOutputStream使用以及与DataInputStream,DataOutput区别
//   结论
//   1.Object相当于装IO流的一个盒子,我们可以把对象比作一个个拼好的积木,IO流就是拼积木的积木块,那么如果要搬走积木(对象),
//   肯定需要把积木(对象)先拆了,再扔进盒子(Object)里,这就是为什么对象要序列化(Serializable)
//   2.当然装的时候我们可以有两种装法一种是全放入(output.writeObject(this))第一种盒子(ObjectInputStream)
//   另一种是分类别 (如:,比如屋顶,地板,这就是流里面的) 放入(output.writeUTF(number),output.writeUTF(name),
//   output.writeInt(age)…)第二种盒子(DataInputStream),所以在搬到另一个地方的时候,
//   第一种盒子里我们把混在一起的积木块倒出((Member)intput.readObject()),
//   第二种盒子则是分块拿出来({input.readUTF(),input.readUTF(),input.readInt()…)
//处理基本类型的时候没有什么很大的区别，区别是Object的可将一个实现了序列化的类实例写入输出流中，
//        ObjectInput可以从输入流中将ObjectOutput输出的类实例读入到一个实例中。DataOutputStream只能处理基本类型。
//        Object处理的类必须是实现了序列化的类

public class DataOutputStreamDemo {
    public static void DataWrite() throws Exception {
        DataOutputStream dos = null;            // 声明数据输出流对象
        File f = new File("d:" + File.separator + "order.txt"); // 文件的保存路径
        dos = new DataOutputStream(new FileOutputStream(f));    // 实例化数据输出流对象
        String names[] = {"衬衣", "手套", "围巾"};    // 商品名称

        float prices[] = {98.3f, 30.3f, 50.5f};        // 商品价格
        int nums[] = {3, 2, 1};    // 商品数量
        for (int i = 0; i < names.length; i++) {    // 循环输出
            dos.writeChars(names[i]);
            dos.writeChar('\t');      // 写入分隔符
            dos.writeFloat(prices[i]);  // 写入价格
            dos.writeChar('\t');      // 写入分隔符
            dos.writeInt(nums[i]);      // 写入数量
            dos.writeChar('\n');    // 换行
        }
        dos.close();    // 关闭输出流
    }

    public static void DataRead() {
        DataInputStream dis = null;        // 声明数据输入流对象
        File f = new File("d:" + File.separator + "order.txt"); // 文件的保存路径
        String name = null;    // 接收名称
        float price = 0.0f;    // 接收价格
        int num = 0;    // 接收数量

        char temp[] = null;    // 接收商品名称
        int len = 0;    // 保存读取数据的个数
        char c = 0;    // '\u0000'
        try {
            dis = new DataInputStream(new FileInputStream(f));    // 实例化数据输入流对象
            while (true) {
                temp = new char[200];    // 开辟空间
                len = 0;
                while ((c = dis.readChar()) != '\t') {    // 接收内容
                    temp[len] = c;
                    len++;    // 读取长度加1
                }

                name = new String(temp, 0, len);    // 将字符数组变为String
                price = dis.readFloat();    // 读取价格
                dis.readChar();    // 读取\t
                num = dis.readInt();    // 读取int
                dis.readChar();    // 读取\n
                System.out.printf("名称：%s；价格：%5.2f；数量：%d \n", name, price, num);
            }
        } catch (Exception e) {
           return;
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) throws Exception {    // 所有异常抛出
        DataWrite();
        DataRead();
    }
}
