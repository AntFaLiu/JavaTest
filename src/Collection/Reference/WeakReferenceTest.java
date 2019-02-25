package collection.Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
//弱引用
//弱引用也是用来描述非必需对象的，但是它的强度比软引用更弱一些，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
// 当GC工作时，无论当前内存是否足够，都会回收掉只被弱引用关联的对象。JDK提供了WeakReference类来实现弱引用。
public class WeakReferenceTest {
    public static void main(String[] args) {

        ReferenceQueue<MyArray> queue = new ReferenceQueue<>();
        WeakReference<MyArray> w = new WeakReference<>(new MyArray(), queue);

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());

        System.gc();
        System.out.println("*****************GC 发生之后************");

        System.out.println(w.get());
        System.out.println(w.isEnqueued());//返回是否被垃圾回收器标记为即将回收的垃圾
        //虽然已经get不到但是队列中仍有保存
        System.out.println(queue.poll());
    }
}
