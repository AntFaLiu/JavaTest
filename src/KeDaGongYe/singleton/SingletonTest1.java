package keDaGongYe.singleton;
/**
    饿汉式单例类。    它在类加载时就立即创建对象。
    比较懒，在类加载时，不创建实例，因此类加载速度快，但运行时获取对象的速度慢
    优点：没有加锁，执行效率高。  用户体验上来说，比懒汉式要好。
    缺点：类加载时就初始化，浪费内存
 */
class SingletonOne {
    private static SingletonOne sin = new SingletonOne();    //直接初始化一个实例对象

    private SingletonOne() {    //private类型的构造函数，保证其他类对象不能直接new一个该对象的实例
    }

    public static SingletonOne getSin() {    //该类唯一的一个public方法
        return sin;
    }
}

class SingletonTwo {
    private static SingletonTwo instance;

    private SingletonTwo() {

    }

    public static synchronized SingletonTwo getInstance() {    //对获取实例的方法进行同步
        if (instance == null)
            instance = new SingletonTwo();
        return instance;
    }
}
/**
 懒汉式单例类。 只在外部对象第一次请求实例的时候才会去创建
 优点：第一次调用时才会初始化，避免内存浪费。
 缺点：必须加锁synchronized 才能保证单例
 */
public class SingletonTest1 {
    private static volatile SingletonTest1 instance = null;
    // !!必须要加volatile限制指令重排序，不然这是双重检验的漏洞

    private SingletonTest1() {
    }

    public static SingletonTest1 getInstance() {
        if (instance == null) {
            synchronized (SingletonTest1.class) {
                if (instance == null) {
                    instance = new SingletonTest1();  //非原子操作
                }
            }
        }
        return instance;
    }
}

//  登记式模式
// 内部类只有在外部类被调用才加载，产生SINGLETON实例
class SingletonThree {
    private SingletonThree() {
    }

    public static final SingletonThree getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final SingletonThree INSTANCE = new SingletonThree();
    }
}

enum SingleEnum {
    INSTANCE;

    SingleEnum() {
        System.out.println("构造函数执行");
    }

    public String getName() {
        return "singleEnum";
    }

    public static void main(String[] args) {
        SingleEnum singleEnum = SingleEnum.INSTANCE;
        System.out.println(singleEnum.getName());
    }
}



