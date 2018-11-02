package Collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
    public static void main(String[] args) {

        // create an empty array deque with an initial capacity
        Deque<Integer> deque = new ArrayDeque<Integer>(8);

        // use add() method to add elements in the deque
        deque.add(25);
        deque.add(30);
        deque.add(20);
        deque.add(18);
        deque.add(15);
        deque.add(13);
        // printing all the elements available in deque
        for (Integer number : deque) {
            System.out.println("Number = " + number);
        }

        int retvalTwo = deque.peek(); //peekFirst()效果相同
        System.out.println("Retrieved peek() Element is " + retvalTwo);
        System.out.println(" after deque.peek(): ");
        // printing all the elements available in deque
        for (Integer number : deque) {
            System.out.println("Number = " + number);
        }
        int retvalTempOne = deque.peekFirst();
        System.out.println("Retrieved peekFirst()  Element is " + retvalTempOne);

        // printing all the elements available in deque
        System.out.println(" after deque.peekFirst(): ");
        for (Integer number : deque) {
            System.out.println("Number = " + number);
        }
        int retval = deque.peekLast();  //取最后一个元素但是不删除
        System.out.println("Retrieved peekLast()  Element is " + retval);

        // printing all the elements available in deque
        System.out.println(" after deque.peekLast(): ");
        for (Integer number : deque) {
            System.out.println("Number = " + number);
        }
        int reTemp = deque.poll();   //和pollFirst（）效果相同
        System.out.println("Element deque.poll() removed is " + reTemp);
        System.out.println(" after deque.poll(): ");
        for (Integer number : deque) {   //遍历Dequeue
            System.out.println("Number = " + number);
        }
        int retvalue = deque.pollFirst();
        System.out.println("Element pollFirst() removed is " + retvalue);
        System.out.println(" after deque.pollFirst(): ");
        for (Integer number : deque) {   //遍历Dequeue
            System.out.println("Number = " + number);
        }
        // 检索并移除此deque队列的最后一个元素。如果此deque队列为空返回null
        int retvalTemp = deque.pollLast();
        System.out.println("Element pollLast() removed is " + retvalTemp);

        System.out.println(" after deque.pollLast(): ");
        // printing all the elements available in deque after using pollLast()
        for (Integer number : deque) {   //遍历Dequeue
            System.out.println("Number = " + number);
        }
    }
}
