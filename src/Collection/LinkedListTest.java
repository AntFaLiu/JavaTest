package Collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        List<String> l = new LinkedList();
        for (int i = 0; i < 100; i++) {
            l.add(i + " ");
        }
        //大小
        System.out.println(l.size());
        //增加
        l.add(2, 50 + " ");

        l.remove(79);

        l.remove("36 ");

        for (int i = 0; i < l.size(); i++) {
            System.out.print(l.get(i));
        }
        System.out.println();
        Iterator<String> listIterator = l.iterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next());
        }
        System.out.println();
        System.out.println("反向遍历： ");
        ListIterator<String> listIterator1 = l.listIterator(l.size());
        while (listIterator1.hasPrevious()) {
            System.out.print(listIterator1.previous());
        }

        System.out.println();
        System.out.println("通过Foreach 打印");
        for(String s : l){
            System.out.print(s);
        }
    }
}
