package Collection;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

//虚引用
//虚引用是最弱的一种引用关系。一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，
//也无法通过虚引用来取得一个对象的实例。由于PhantomReference来重写了获取对象的get方法，
//永远返回null。为一个对象设置虚引用关联的唯一目的就是能在这个对象被GC回收时收到一个系统通知
public class ReferenceQueueTest {
    public static void main(String[] args) {
        ReferenceQueue<MyArray> queue = new ReferenceQueue<>();
        PhantomReference<MyArray> ptr = new PhantomReference<>(new MyArray(), queue);

        System.gc(); //此处启动GC，回收A对象，queue收到通知，该对象回收了

        if (ptr.isEnqueued()) {
            System.out.println("ptr.isEnqueued()");
        } else {
            System.out.println("not ptr.isEnqueued()");
        }
    }
}
