package caiYuan.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {
        charsetDemo();
    }

    public static void charsetDemo() {

        //获得虚拟机默认的编码方式
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("虚拟机默认的编码方式:" + defaultCharset);
        //获得系统支持的所有编码方式
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entrySet = availableCharsets.entrySet();
        Iterator<Map.Entry<String, Charset>> iterator = entrySet.iterator();

        System.out.println("系统支持的所有编码方式:");
        while (iterator.hasNext()) {
            Map.Entry<java.lang.String, java.nio.charset.Charset> entry = (Map.Entry<java.lang.String, java.nio.charset.Charset>) iterator
                    .next();
            System.out.println("key: " + entry.getKey() + "    value: " + entry.getValue());
        }
    }
}
