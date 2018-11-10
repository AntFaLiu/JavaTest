package MyCharsetTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CharsetTest {

    public static void main(String[] args) {
        //charsetDemo();
        urlEncodeAndDecodeDemo();
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

        System.out.println("======================================判断是否支持该编码类型===============================================");
        //判断是否支持该编码类型
        String charsetName = "UTF-8";
        boolean supported = Charset.isSupported(charsetName);
        System.out.println(supported);

        try {
            /**
             *使用charset进行编码和解码
             *charset如何使用
             */
            //得到一个CharSet实例
            Charset charsetInstance = Charset.forName(charsetName);

            //创建一个编码器实例
            CharsetEncoder encoder = charsetInstance.newEncoder();

            //创建一个接码器实例
            CharsetDecoder decoder = charsetInstance.newDecoder();

            CharBuffer wrap = CharBuffer.wrap("刘宇鹏".toCharArray());

            //使用encoder进行解码返回一个ByteBuffer
            ByteBuffer encode = encoder.encode(wrap);

            System.out.println("encode:" + encode);

            //ByteBuffer传递给decoder进行编码，返回一个CharBuffer
            CharBuffer decode = decoder.decode(encode);

            String string = decode.toString();

            System.out.println("decode.toString(): " + string);

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
        }
    }

    public static void urlEncodeAndDecodeDemo() {

        /**
         * 对 String 编码时，使用以下规则：
         * 字母数字字符 "a" 到 "z"、"A" 到 "Z" 和 "0" 到 "9" 保持不变。
         * 特殊字符 "."、"-"、"*" 和 "_" 保持不变。
         * 空格字符 " " 转换为一个加号 "+"。
         * 所有其他字符都是不安全的，因此首先使用一些编码机制将它们转换为一个或多个字节。
         * 然后每个字节用一个包含3个字符的字符串 "%xy" 表示，其中 xy 为该字节的两位十六进制表示形式。
         * 推荐的编码机制是 UTF-8。但是，出于兼容性考虑，如果未指定一种编码，则使用相应平台的默认编码。
         * 例如，使用 UTF-8 编码机制，字符串 "The string ü@foo-bar" 将转换为 "The+string+%C3%BC%40foo-bar"，
         * 因为在 UTF-8 中，字符 ü 编码为两个字节，C3 （十六进制）和 BC （十六进制），
         * 字符 @ 编码为一个字节 40 （十六进制）。
         */

        try {
            // 将application/x-www-from-urlencoded字符串转换成普通字符串
            String keyWord = URLDecoder.decode("%C4%E3%BA%C3", "GBK");
            System.out.println(keyWord);  //输出你好

            // 将普通字符创转换成application/x-www-from-urlencoded字符串
            String urlString = URLEncoder.encode("你好", "GBK");  //输出%C4%E3%BA%C3
            System.out.println(urlString);
        } catch (UnsupportedEncodingException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
