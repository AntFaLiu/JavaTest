package lockTest.ReadAndWriteLock;

public class JusetReadSync {
    public synchronized void get(Thread thread) {
        System.out.println("start time:"+System.currentTimeMillis());
        for(int i=0; i<5; i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:"+System.currentTimeMillis());
    }

    public static void main(String[] args) {
        final JusetReadSync jusetReadSync = new JusetReadSync();
        new Thread(new Runnable() {
            @Override
            public void run() {
                jusetReadSync.get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                jusetReadSync.get(Thread.currentThread());
            }
        }).start();
    }
}
