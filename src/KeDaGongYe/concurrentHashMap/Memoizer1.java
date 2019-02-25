//package keDaGongYe.concurrentHashMap;
//
//import java.util.HashMap;
//
//public class Memoizer1 {
//    private final Map<A, V> cache = new HashMap<>();
//
//
//    public Memoizer1(Computable<A, V> c) {
//        this.c = c;
//    }
//    static class Worker{
//        public synchronized V compute(A arg) throws InterruptedException {
//            V result = cache.get(arg);
//            if (result == null) {
//                result = c.compute(arg);
//                cache.put(arg, result);
//            }
//            return result;
//        }
//    }
//
//
//}
