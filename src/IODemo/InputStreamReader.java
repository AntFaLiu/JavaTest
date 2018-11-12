package IODemo;

import sun.nio.cs.StreamDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class InputStreamReader extends Reader {
    public static void main(String[] args) {

    }

    private final StreamDecoder sd;

    //构造函数，使用平台默认的字符类型
    public InputStreamReader(InputStream in) {
        super(in);
        try {
            sd = StreamDecoder.forInputStreamReader(in, this, (String)null); // ## check lock object
        } catch (UnsupportedEncodingException e) {
            // The default encoding should always be available
            throw new Error(e);
        }
    }

    /**
     * Creates an InputStreamReader that uses the named charset.
     *
     * @param  in
     *         An InputStream
     *
     * @param  charsetName
     *         The name of a supported
     *         {@link java.nio.charset.Charset charset}
     *
     * @exception  UnsupportedEncodingException
     *             If the named charset is not supported
     */
    //构造函数，使用提供的字符类型
    public InputStreamReader(InputStream in, String charsetName)throws UnsupportedEncodingException
    {
        super(in);
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        sd = StreamDecoder.forInputStreamReader(in, this, charsetName);
    }

    //构造函数，使用提供的字符类型
    public InputStreamReader(InputStream in, Charset cs) {
        super(in);
        if (cs == null)
            throw new NullPointerException("charset");
        sd = StreamDecoder.forInputStreamReader(in, this, cs);
    }
    //使用提供的字符译码器
    public InputStreamReader(InputStream in, CharsetDecoder dec) {
        super(in);
        if (dec == null)
            throw new NullPointerException("charset decoder");
        sd = StreamDecoder.forInputStreamReader(in, this, dec);
    }

    //返回字符编码
    public String getEncoding() {
        return sd.getEncoding();
    }

    //读单个字符
    public int read() throws IOException {
        return sd.read();
    }
    //读字符到cbuf中
    public int read(char cbuf[], int offset, int length) throws IOException {
        return sd.read(cbuf, offset, length);
    }
    //是否准备好读
    public boolean ready() throws IOException {
        return sd.ready();
    }
    //关闭资源
    public void close() throws IOException {
        sd.close();
    }
}

