package Thread.Volatile;

public class VolatileDemo1 {
    volatile int a = 10;

    public static void main(String[] args) {
        VolatileDemo1 volatileDemo1 = new VolatileDemo1();
        volatileDemo1.get();
    }

    public void get() {
        int b = a;
        int c = a;
        System.out.println("a: " + a + " b: " + c);
    }
}
