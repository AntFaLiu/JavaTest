package MyCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Add {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        List<Integer> list1 = new LinkedList<>();

        Long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i % 100);
        }
        list.remove(32);
        list.remove(2);
        long l1 = System.currentTimeMillis();
        for (int j = 0; j < 10000000; j++) {
            list1.add(j % 100);
        }
        list1.remove(1);
        list1.remove(42);
        long l2 = System.currentTimeMillis();
        System.out.println("ArrayList: " + (l1 - l));
        System.out.println("LinedList: " + (l2 - l1));
    }
}
