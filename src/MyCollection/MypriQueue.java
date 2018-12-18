package MyCollection;

import java.util.Arrays;

public class MypriQueue {
    static Integer[] queue = new Integer[11];
    static int size = 0;

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
        for (i = (size - 1) / 2; i >= 0; i--) {
            Heap_Adjust(arr, i, arr.length);    //构建小根堆   就是根节点是最大的数
        }
    }

    public static boolean offer(Integer e) {
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }

    private static void siftUp(int k, Integer x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Integer e = queue[parent];
            if (x > e)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    private static void grow(int minCapacity) {

        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - Integer.MAX_VALUE > 0)
            newCapacity = Integer.MAX_VALUE;
        queue = Arrays.copyOf(queue, newCapacity);
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
        MypriQueue queue = new MypriQueue();
    }
}
