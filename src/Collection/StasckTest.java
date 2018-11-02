package Collection;

import java.util.Stack;

public class StasckTest {
    static Stack<Integer> s  = new Stack<Integer>();
    public static void main(String[] args) {
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        System.out.println(s.pop());
    }
}
