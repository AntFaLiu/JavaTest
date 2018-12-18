package MyCollection;

import java.util.Arrays;

public class MinHeap {
    /**
     * 默认容量
     */
    private static final int MINCAPACITY = 11;
    /**
     * 数据个数
     */
    private static final int MAXNUM = 100000;
    /**
     * 求最小数的个数
     */
    private static final int MINNUMBER = 10;
    /**
     * 最大容量
     */
    public int allSize;
    /**
     * 已使用容量
     */
    public int usedSize;
    /**
     * 存储用数组
     */
    public int[] arr;

    /**
     * 无参构造
     */
    public MinHeap() {
        this.allSize = MINCAPACITY;
        this.usedSize = -1;
        this.arr = new int[MINCAPACITY];
    }

    /**
     * 有参构造
     *
     * @param capacity 自定义容量
     * @throws Exception 指定容量异常
     */
    public MinHeap(int capacity) throws Exception {
        if (capacity < 0) {
            throw new Exception("错误的容量！" + capacity);
        }
        if (capacity == 0) {
            capacity = 1;
        }
        this.allSize = capacity;
        this.usedSize = -1;
        this.arr = new int[capacity];
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        /*Random random = new Random();
        for (int i = 0; i < MAXNUM; i++) {
            minHeap.add(random.nextInt(MAXNUM));
        }
        minHeap.print();
        long l = System.currentTimeMillis();
        minHeap.getTenMinNumber();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);*/
        minHeap.add(20);
        minHeap.add(10);
        minHeap.add(10);
        minHeap.add(40);
        minHeap.add(15);
        minHeap.add(50);
        minHeap.add(1);
        minHeap.add(12);
        minHeap.add(22);
        minHeap.print();
    }

    /**
     * 添加元素
     *
     * @param value 元素值
     */
    public void add(int value) {
        if (usedSize == arr.length - 1) {
            arr = Arrays.copyOfRange(arr, 0, 2 * arr.length);
        }
        arr[++usedSize] = value;
        int fatherIndex = (usedSize - 1) >> 1;
        int sonIndex = usedSize;
        rightAdjust(fatherIndex, sonIndex);
    }

    /**
     * 向上调整
     */
    private void rightAdjust(int fatherIndex, int sonIndex) {
        if (usedSize == 0) {
            return;
        }
        while (fatherIndex >= 0) {
            int tmp = arr[fatherIndex];
            if (tmp < arr[sonIndex]) {
                break;
            } else {
                arr[fatherIndex] = arr[sonIndex];
                arr[sonIndex] = tmp;
            }
            sonIndex = fatherIndex;
            fatherIndex = (fatherIndex - 1) >> 1;
        }
    }

    /**
     * 数组元素输出
     */
    public void print() {
        for (int i = 0; i <= usedSize; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}