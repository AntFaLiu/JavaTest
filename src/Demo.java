//反编译的方式讲解i++ 和 ++i的区别

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 45, 63, 78};
        int[] brr = arr.clone();
        System.out.println("brr: "+ Arrays.toString(arr));
        int i = 0;
        int j = 1;
        int k = 2;
        System.out.println(i++);
        System.out.println(i);
        i = i++;
        j = ++j;
        k += 2;   //就没有一个入栈的过程了
//        System.out.println(i+j+k);
    }
}

//前面6条指令分别给i,j,k变量进行赋值，即
//        0: iconst_0—–把常量0入栈
//        1: istore_1——把栈顶元素（0）存入第一个变量i中
//        2: iconst_1—–把常量1入栈
//        3: istore_2—–把栈顶元素（1）存入第二个变量j中
//        4:iconst_2—–把常量2入栈
//        5: istore_3—–把栈顶元素（2）存入第三个变量k中
//
//        接下来的
//        6,7,10对应的代码是i = i++;     也就是i++一共经历了三步
//        6: iload_1——把第一个变量的值压入栈
//        7:iinc 1, 1——第一个变量的值加1
//        10: istore_1——把栈顶元素存入第一个变量i
//
//        11,14,15对应的是j = ++j;
//        11: iinc 2, 1—–把第二个变量j的值加1
//        14: iload_2—-把第二个变量是j的值压入栈
//        15: istore_2—-把栈顶元素存入第二个变量j中
//       16对应的就是k += 2;
//        16: iinc 3, 2—把第三变量的值加二

//  synchronized 使用实例Hashtable
//    public synchronized V get(Object key) {
//        Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        for (Hashtable.Entry<?,?> e = tab[index]; e != null ; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                return (V)e.value;
//            }
//        }
//        return null;
//    }

//    JDK示例-2：Collections提供线程安全转换方法
//    在JDK中，Collections有一个方法可以把不是线程安全的集合转化为线性安全的集合，它是这样实现的：
//
//
//    public static <T> collection<T> synchronizedCollection(collection<T> c) {
//        return new SynchronizedCollection<>(c);
//    }
//    static class SynchronizedCollection<E> implements collection<E>, Serializable {
//        private static final long serialVersionUID = 3053995032091335093L;
//
//        final collection<E> c;  // Backing collection
//        final Object mutex;     // Object on which to synchronize
//
//        SynchronizedCollection(collection<E> c) {
//            this.c = Objects.requireNonNull(c);
//            mutex = this;
//        }
//
//        SynchronizedCollection(collection<E> c, Object mutex) {
//            this.c = Objects.requireNonNull(c);
//            this.mutex = Objects.requireNonNull(mutex);
//        }
//
//        public int size() {
//            synchronized (mutex) {return c.size();}
//        }
//
//        ......
//    }

//可以看到 在构造函数中 有mutex = this, 然后在具体的方法使用了 synchronized(mutex)，这样就会对调用该方法的对象进行加锁。还是会出现HashTable 出现的那种问题，也就是效率不高。


//当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的对象来充当锁：
//class Test implements Runnable {
//    private byte[] lock = new byte[0];  // 特殊的instance变量
//
//    public void method() {
//        synchronized (lock) {
//            for (int i = 0; i < 5; i++) {
//                System.out.println(thread.currentThread().getName() + " synchronized loop " + i);
//            }
//        }
//    }
//
//    public void run() {
//
//    }
//}
//        putTest();
//        getTest();
//        method();
//        method1();