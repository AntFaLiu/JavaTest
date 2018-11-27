package IODemo;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

public class PushbackReaderDemo {
    public static void main(String[] args) {

//        String s = "Hello World";
//
//        // create a new StringReader
//        StringReader sr = new StringReader(s);
//
//        // create a new PushBack reader based on our string reader
//        PushbackReader pr = new PushbackReader(sr, 20);
//
//        try {
//            // read the first five chars
//            for (int i = 0; i < 5; i++) {
//                char c = (char) pr.read();
//                System.out.print("" + c);
//            }
//
//            // change line
//            System.out.println();
//
//            // check if mark is supported by this class
//            System.out.println("" + pr.markSupported());
//
//            // close the stream
//            pr.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
        try {
            String s = "Hello World";

            // create a new StringReader
            StringReader sr = new StringReader(s);

            // create a new PushBack reader based on our string reader
            PushbackReader pr = new PushbackReader(sr, 20);

            // read the first five chars
            for (int i = 0; i < 5; i++) {
                char c = (char) pr.read();
                System.out.print("" + c);
            }

            // change line
            System.out.println();

            // try to reset and get an exception because it is not supported
            pr.reset();

            // close the stream
            pr.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
