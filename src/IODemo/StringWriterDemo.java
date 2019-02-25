package ioDemo;

import java.io.StringWriter;

public class StringWriterDemo {
    public static void main(String[] args) {

        // create a new writer
        StringWriter sw = new StringWriter();

        // create two new sequences
//        CharSequence与String都能用于定义字符串，但CharSequence的值是可读可写序列，而String的值是只读序列。
        CharSequence sq1 = "Hello";
        CharSequence sq2 = " World";

        // append sequence
        sw.append(sq1);

        // print result
        System.out.println("" + sw.toString());

        // append second sequence
        sw.append(sq2);

        // print result again
        System.out.println("" + sw.toString());

    }
}
