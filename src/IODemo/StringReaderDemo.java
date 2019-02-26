package ioDemo;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;

public class StringReaderDemo {
    public static void main(String[] args) {

//        String s = "Hello world";
//
//        // create a StringReader
//        StringReader reader = new StringReader(s);
//
//        // create a char array to read chars into
//        char cbuf[] = new char[5];
//
//        try {
//            // read characters into a portion of an array.
//            reader.read(cbuf, 0, 5);
//            reader.re
//            // print cbuf
//            System.out.println(cbuf);
//
//            // Close the stream
//            reader.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        String s = "Hello world";

        // create a new Char Buffer with capacity of 12
        CharBuffer cb = CharBuffer.allocate(12);

        // create a StringReader
        Reader reader = new StringReader(s);

        try {
            // read characters into a char buffer
            reader.read(cb);

            // flip the char buffer
            cb.flip();

            // print the char buffer
            System.out.println(cb.toString());

            // Close the stream
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
