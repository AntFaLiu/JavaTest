package Collection;

public class HashMap {

    public static void main(String[] args) {
        int n = get(20);
        String result = toBinaryString(n);
        System.out.println("20 : " + toBinaryString(20));
        System.out.println("result: " + result);
    }

    //判断n是否越界，返回 2的n次方作为 table（哈希桶）的阈值
    public static int get(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println("n: " + n);
        return n;
    }

    public static String toBinaryString(int num) {
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

