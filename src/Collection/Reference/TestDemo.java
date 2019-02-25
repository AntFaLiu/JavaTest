package collection.Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

class A {
    byte[] array = new byte[1024 * 1024]; //被软引用的对象占用了1M的内存
}

public class TestDemo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ReferenceQueue<A> queue = new ReferenceQueue<A>();
        SoftReference<A> w = new SoftReference<A>(new A(), queue);

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());

//此处需要3M的内存，内存受限，不够用了，因此触发GC，回收软引用对象
        try {
            System.gc();
            byte[] array = new byte[3 * 1024 * 1024];

        } catch (Exception e) {
            //System.out.println();
        } finally {
            System.out.println(w.get());
            System.out.println(w.isEnqueued());
            System.out.println(queue.poll());
        }


    }
}
