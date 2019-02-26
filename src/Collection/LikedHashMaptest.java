package collection;


import java.util.LinkedHashMap;

//在linkedhashMap中维护了一条双向链表，解决了
public class LikedHashMaptest {
    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap();

        int a = 100;
        int b = 7;
        int d = 8;
        int c = a % b;
        System.out.println("a%b: " + c);
        c = a & (b - 1);
        System.out.println("a&(b-1): " + c);
        int e = a %d;
        System.out.println("a%e: " + e);
        e = a & (d - 1);
        System.out.println("a&(d-1): " + e);
    }
}
