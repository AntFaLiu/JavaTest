package Collection.Queue;

import java.util.Arrays;
import java.util.LinkedList;

public class CreateHeapTest {


    public static void Heap_Adjust(Integer[] arr, int start, int end) {
        int tmp = arr[start];
        int parent = start;//start 父节点

        for (int i = 2 * start + 1; i <= end; i = i * 2 + 1) {
            if (i + 1 < end && arr[i] > arr[i + 1])//求左右子树中较小的
            {
                i++;
            }
            if (arr[i] < tmp) {
                arr[parent] = arr[i];
                parent = i;
            } else {
                break;
            }
        }
        arr[parent] = tmp;
    }

    public static void CreateHeap(Integer[] arr, int size) {
        int i = 0;
        for (i = (size - 1 - 1) / 2; i >= 0; i--) {
            Heap_Adjust(arr, i, arr.length);    //构建小根堆   就是根节点是最大的数
        }
    }

    public static void set(int i, Integer e, Integer[] arr, int size) {
        int s = --size;
        arr[i] = e;
        Heap_Adjust(arr, i, size);  //先向下调整  再向上调整
        if (arr[i] == e) {
            siftUp(i, e, arr);
        }
    }

    private static void siftUp(int k, Integer x, Integer[] arr) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Integer e = arr[parent];
            if (x > e)
                break;
            arr[k] = e;
            k = parent;
        }
        arr[k] = x;
    }

    public static int poll(Integer[] arr) {
        int size = arr.length;
        int index = --size;
        int result = arr[0];
        arr[0] = arr[index];
        arr[index] = null;
        Heap_Adjust(arr, 0, index);
        return result;
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        Integer[] arr = new Integer[]{8, 6, 16, 10, 13, 14, 18, 17, 25, 5, 11, 12, 58, 69, 77, 35, 22, 37};
        CreateHeap(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        set(arr.length - 1, 33, arr, arr.length);
        System.out.println(Arrays.toString(arr));
        int value = poll(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("poll value: " + value);
    }

}
