package lockTest.ReadAndWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
    private static Map<String, Object> cacheMap = new HashMap<String, Object>();

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String obj = (String) get("TestKey");
                    System.out.println(obj);
                }
            }).start();
        }
    }

    public static Object get(String key) {
        lock.readLock().lock(); // 先加读锁
        Object value = null;
        try {
            value = cacheMap.get(key);
            if (value == null) { // 若不存在cache中
                // 让线程sleep 1秒方便测试
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("****** lock.readLock().unlock()  ******");
                lock.readLock().unlock(); // 若果value为空 则释放掉读锁，让该线程获取写锁，而其他线程只能等待该写锁释放，才能在进读锁
                System.out.println("******   lock.writeLock().lock()  ****");
                lock.writeLock().lock(); // 加写锁

                value = cacheMap.get(key);
                try {
                    if (value == null) {
                        value = "Cache Data";// 查询数据库 ,从DB中获取数据
                        // 存入缓存中
                        cacheMap.put(key, value);
                    }
                } finally {
                    lock.readLock().lock();
                    lock.writeLock().unlock();
                }
            }
        } finally {
            lock.readLock().unlock(); // 释放第一次获取的读锁
        }

        return value;
    }
}
