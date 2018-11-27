package IODemo;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayDemo {
    public static void main(String[] args) throws IOException {
        char[] letters = new char[]{'b', 'c', 'd', 'e', 'f', 'h', 'i', 'j'};
        testCharArrayWriter(letters);
        System.out.println("-----------------------------------------------------------");
        testCharArrayReader(letters);
    }

    //测试testCharArrayWriter
    private static void testCharArrayWriter(char[] letters) throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        caw.write('a');
        caw.write(letters, 0, 4);
        caw.write("world", 1, 3);
        caw.append('0').append("123").append("678910", 0, 2);
        String result = caw.toString();
        System.out.println("缓冲区数据转化成字符串:  " + result);
        char[] charArray = caw.toCharArray();
        System.out.println("缓冲区数据: " + new String(charArray));

        CharArrayWriter anotherWriter = new CharArrayWriter();
        caw.writeTo(anotherWriter);
        System.out.println("字符数组anotherWriter的数据:   " + anotherWriter.toString());
    }

    //测试testCharArrayReader
    private static void testCharArrayReader(char[] letters) throws IOException {
        System.out.println("数据: " + new String(letters));
        CharArrayReader car = new CharArrayReader(letters);
        //下一个字节是否可读
        if (car.ready()) {
            System.out.println("单个字节:  " + (char) car.read());
        }
        char[] letterChar = new char[10];
        car.read(letterChar, 0, 3);
        System.out.println("字符数组letterChar:" + String.valueOf(letterChar));
        //mark(0),0不表示实际意义,mark的是当前位置
        if (car.markSupported()) {
            car.mark(0);
        }
        //跳过三个字节
        car.skip(3);
        System.out.println("此时位置:  " + (char) car.read());
        car.reset();
        System.out.println("此时位置:  " + (char) car.read());
    }
}
