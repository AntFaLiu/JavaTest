package collection;


import java.util.HashMap;
import java.util.Map;
class Apple{
    int num;
    int price;
}

public class HashMapTest {

    public static Map data = new HashMap();

    public static void main(String[] args) {
        Apple a = new Apple();
        a.num = 15;
        a.price = 19;

        data.put("apple",a);
        System.out.println(a);
        System.out.println(data.size());
//        data.containsKey();
//        data.containsValue();
//        data.remove(); //传入一个Key
//        data.replace();
        int value = (20 - 1) << 1;
        int n = get(value);
        String result = toBinaryString(n);
        System.out.println("20 : " + toBinaryString(20));
        System.out.println("result: " + result);
        data.put("lup", null);
        data.put("lup", null);
        data.put("lyp", null);
        data.put("lur", null);
        data.put("lur", null);
        data.put("lud", null);
        data.put("lud", "aaa");
        System.out.println("map:  " + data);
        System.out.println(data.containsKey("lyp"));
        System.out.println(data.containsValue("aaa"));
        data.remove("lup");
        data.remove("lud","aaa");
        System.out.println("map:  " + data);
    }

    //判断n是否越界，返回 2的n次方作为 table（哈希桶）的阈值
    public static int get(int n) {
        // int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int x = n - (n >>> 1);
        System.out.println("n: " + x);
        return n;
    }

    public static String toBinaryString(int num) {  //
        if (num == 0) return "" + 0;
        String result = "";
        // 左面0的个数
        int n = Integer.numberOfLeadingZeros(num);
        num <<= n;
        for (int i = 0; i < 32 - n; ++i) {
            int x = (Integer.numberOfLeadingZeros(num) == 0) ? 1 : 0;
            result += x;
            num <<= 1;
        }
        return result;
    }
}

