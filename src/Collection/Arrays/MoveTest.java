package Collection.Arrays;

public class MoveTest {
    public static void main(String[] args) {
        int[] arr = new int[7];
        for (int i = 0; i < 6; i++) {
            arr[i] = i;
        }
        add(arr, 6, 2, 100);
//        delete(arr, 3);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }

    public static int delete(int[] arr, int index) {
        int result = arr[index];
        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = 0;
        return result;
    }

    public static void add(int[] arr, int size, int index, int value) {
        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = value;
    }
}
