package thread.Volatile;

public class NoVisibility implements Runnable{

    private String name;

    public NoVisibility(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name  + ":" + i);
            if ("t1".equals(name) && i == 5) {
                System.out.println(name  + ":" + i +"......yield.............");
                Thread.yield();
            }
        }
    }

    /**
     * 暂停当前正在执行的线程对象，并执行其他线程
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new NoVisibility("t1"));
        Thread t2 = new Thread(new NoVisibility("t2"));
        t1.start();
        t2.start();
    }
}
