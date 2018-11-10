package Collection;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

//软引用
//软引用是用来描述一些还有用但并非必须的对象。对于软引用关联的对象，在系统将要发生内存溢出异常之前，
// 将会把这些对象列进回收范围之中再次进行回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。
// JDK提供了SoftReference类来实现软引用。
class MyArray {
    byte[] array = new byte[650 * 1024]; //被软引用的对象占用了650K的内存
}

public class SoftReferenceTest {
    private SoftReference<Object> sf;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ReferenceQueue<MyArray> queue = new ReferenceQueue<MyArray>();
        SoftReference<MyArray> w = new SoftReference<MyArray>(new MyArray(), queue);

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());

        System.gc();

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());
    }
}
