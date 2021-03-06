package lockTest.ReLock.atomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicDemo {
    public static void main(String[] args) {
        People people1 = new People("Bom", 0);
        People people2 = new People("Tom", 10);

        //先初始化一个值，如果不初始化则默认值为null
        AtomicReference<People> reference = new AtomicReference<>(people1);
        People people3 = reference.get();
        if (people3.equals(people1)) {
            System.out.println("people3:" + people3);
        } else {
            System.out.println("else:" + people3);
        }

        /**
         * 当前值：拿当前值和reference.get()获取到的值去比较，如果相等则true并更新值为期望值
         * 期望值：如果返回true则更新为期望值，如果返回false则不更新值0
         */
        boolean b = reference.compareAndSet(null, people2);
        System.out.println("myClass.main-" + b + "--" + reference.get());

        boolean b1 = reference.compareAndSet(people1, people2);
        System.out.println("myClass.main-" + b1 + "--" + reference.get());
    }
}
