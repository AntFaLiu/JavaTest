package LockTest;
/*
    synchronized
*/

public class SynLock implements Runnable {

    // 修饰代码块 - 对象锁

    /*
     * 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。
     * 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
     * 当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
     * 其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞
     * */

    public void run() {
//        synchronized (this) {    //synchronized 修饰代码块
//            for (int i = 0; i < 5; i++) {
//                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
//            }
//        }
        putTest();
//        getTest();
//        method();
//        method1();
    }

    //修饰一个方法 - 对象锁
    /*
     * 在定义接口方法时不能使用synchronized关键字。
     * 构造方法不能使用synchronized关键字，但可以使用synchronized代码块来进行同步。
     * synchronized 关键字不能被继承 。如果子类覆盖了父类的 被 synchronized 关键字修饰的方法，那么子-
     * 类的该方法只要没有 synchronized 关键字，那么就默认没有同步，也就是说，不能继承父类的 synchronized。
     * */
    public synchronized void getTest() {  //对方法上锁
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    //修饰一个类 - 类锁
    //其作用的范围是synchronized后面括号括起来的部分，作用的对象是这个类的所有对象
    public void putTest() {
        synchronized (SynLock.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    //修饰静态方法 - 类锁
    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    //当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的对象来充当锁：
    private byte[] lock = new byte[0];  // 特殊的instance变量

    public void method1() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        SynLock t1 = new SynLock();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }


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
//    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
//        return new SynchronizedCollection<>(c);
//    }
//    static class SynchronizedCollection<E> implements Collection<E>, Serializable {
//        private static final long serialVersionUID = 3053995032091335093L;
//
//        final Collection<E> c;  // Backing Collection
//        final Object mutex;     // Object on which to synchronize
//
//        SynchronizedCollection(Collection<E> c) {
//            this.c = Objects.requireNonNull(c);
//            mutex = this;
//        }
//
//        SynchronizedCollection(Collection<E> c, Object mutex) {
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

}


//当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的对象来充当锁：
class Test implements Runnable {
    private byte[] lock = new byte[0];  // 特殊的instance变量

    public void method() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public void run() {

    }
}