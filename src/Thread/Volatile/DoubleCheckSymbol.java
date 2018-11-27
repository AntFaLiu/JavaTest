package Thread.Volatile;

public class DoubleCheckSymbol {
    private static  DoubleCheckSymbol d;
    private DoubleCheckSymbol() {}
    public static DoubleCheckSymbol getSymbol() {
        if (d == null) {
            synchronized(DoubleCheckSymbol.class) {
                if (d == null) {
                    d = new DoubleCheckSymbol();
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
//        DoubleCheckSymbol doubleCheckSymbol = new DoubleCheckSymbol();
//        doubleCheckSymbol.getSymbol();
        System.out.println(DoubleCheckSymbol.getSymbol());
    }
}
