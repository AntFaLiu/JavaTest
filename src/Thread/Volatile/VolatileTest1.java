package Thread.Volatile;

public class VolatileTest1 {
    static boolean flag;
    static int context;
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                context = 1;
                flag = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    return;
                }
                doSomethingwithconfig(context);
            }
        }).start();
    }

    public static void doSomethingwithconfig(int context) {
        context++;
        System.out.println(context);
    }

    private static int loadContext() {
        return 100;
    }
}
