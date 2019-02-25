package caiYuan.io.IoHomeWork;

public class IsChat {
    public static void main(String[] args) {
        char a = ' ';
        char b = 0;
        char c = 'a';
        char d = '中';

        System.out.println(isChineseChar(a));
        System.out.println(isChineseChar(b));
        System.out.println(isChineseChar(c));
        System.out.println(isChineseChar(d));
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }
}
