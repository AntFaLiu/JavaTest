package Thread.Volatile;

public class NoVisibility {
    private static volatile boolean ready = false;
    private static volatile int number;

    public static void main(String[] args) {
        new ReaderThread().start();

        number = 42;
        ready = true;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}
