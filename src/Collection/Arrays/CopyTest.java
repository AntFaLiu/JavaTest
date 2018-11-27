package Collection.Arrays;

import java.util.Arrays;

public class CopyTest {
    public static void main(String[] args) {
        int[] a = {1, 25, 65, 98, 45, 64};
        int[] b = {2, 4, 8, 7, 10, 0, 0, 0, 0, 0};

        System.arraycopy(a, 2, b, 2, 3);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(Arrays.copyOf(a, 5)));
        System.out.println(Arrays.toString(a.clone()));
        System.out.println(Arrays.toString(Arrays.copyOfRange(a,2,6)));
    }
}
