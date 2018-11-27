package Collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayLinkedTest {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        List<Integer> lrr = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            arr.add(i);
            lrr.add(i);
        }
        Long l = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            arr.get(i);
        }
        Long l2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            lrr.get(i);
        }
        Long l3 = System.currentTimeMillis();
        System.out.println("t1:  " + (l2 - l) + "\n t2:" + (l3 - l2));
    }
}
