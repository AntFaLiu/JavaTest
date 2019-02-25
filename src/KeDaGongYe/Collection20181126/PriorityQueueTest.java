package keDaGongYe.Collection20181126;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue();
        for (int i = 0; i < 370;  i = (i+1)*2) {
            queue.add(i);
        }
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        queue.offer(8);
        System.out.println();
        iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        System.out.println(queue.element());
        System.out.println(queue.peek());



    }
}
