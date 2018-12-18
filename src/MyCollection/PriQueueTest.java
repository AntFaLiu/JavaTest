package MyCollection;

public class PriQueueTest {
    public static void main(String[] args) {
        int[] num = {2, 6, 3, 5, 8, 9, 1, 7, 4};

        for (int i = num.length - 1; i > 0; i--) {
            change(num, i);
        }
        for (int i : num) {
            System.out.println(i);
        }
    }

    public static void change(int[] num, int i) {
        int temp = 0;
        if (i > 0) {
            if (num[i] < num[(i - 1) >>> 2]) {
                temp = num[i];
                num[i] = num[(i - 1) >>> 2];
                num[(i - 1) >>> 2] = temp;
            }
            change(num, (i - 1) >>> 2);
        } else {
            return;
        }

    }
}
