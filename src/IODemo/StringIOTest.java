package ioDemo;

import java.io.StringReader;
import java.io.StringWriter;

public class StringIOTest {
    public static void main(String[] args) {
        try (StringReader sr = new StringReader("just a test~");
             StringWriter sw = new StringWriter()) {
            int c = -1;
            while((c = sr.read()) != -1){
                sw.write(c);
            }
            //这里展示了即使关闭了StringWriter流，但仍然能获取到数据，因为其close方法是一个空实现。
            sw.close();
            System.out.println(sw.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
