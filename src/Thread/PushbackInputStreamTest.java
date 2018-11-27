package Thread;

import java.io.*;

public class PushbackInputStreamTest {
    public static void main(String args[]) throws Exception
    {
//        PushbackInputStream in = new PushbackInputStream(System.in);
//        in.unread('a');
//        int ch = -1;
//        System.out.println(in.read());
//        while ((ch=in.read()) != -1){
//            System.out.println((char) ch);
//
//        }
//        in.close();
//        PushbackReader reader = new PushbackReader(new InputStreamReader(System.in));
//        reader.unread('b');
//        ch = reader.read();
//        System.out.println((char) ch);
//        reader.close();

//        public void unread(char[] cbuf)
        String s = "Hello World";

        // create a new StringReader
        StringReader sr = new StringReader(s);

        // create a new PushBack reader based on our string reader
        PushbackReader pr = new PushbackReader(sr, 20);

        try {
            // read the first five chars
            for (int i = 0; i < 5; i++) {
                char c = (char) pr.read();
                System.out.print("" + c);
            }

            // change line
            System.out.println();

            // create a new array to unread
            //char cbuf[] = {'w', 'o', 'r', 'l', 'd'};

            // unread into cbuf
            //pr.unread(cbuf);

            // read five chars, which is what we unread from cbuf
            for (int i = 0; i < 5; i++) {
                char c = (char) pr.read();
                System.out.print("" + c);
            }

            // close the stream
            pr.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
