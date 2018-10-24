## Arrays
### sort()
    public static void sort(int[] a) {
    DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0); //使用双轴快速排序
    }


    if (right - left < QUICKSORT_THRESHOLD) {
    sort(a, left, right, true);
    return;
    }

    可以发现如果数组的长度小于QUICKSORT_THRESHOLD的话就会使用这个双轴快速排序，而这个值是286。
    那如果大于286呢，它就会坚持数组的连续升序和连续降序性好不好，如果好的话就用归并排序，不好的话就用快速排序，看下面这段注释就可以看出
    if (length < INSERTION_SORT_THRESHOLD)
    即如果数组长度小于INSERTION_SORT_THRESHOLD(值为47)的话，那么就会用插入排序了，不然再用双轴快速排序。
    所以总结一下Arrays.sort()方法，如果数组长度大于等于286且连续性好的话，就用归并排序，如果大于等于286且连续性不好的话就用双轴快速排序。如果长度小于286且大于等于47的话就用双轴快速排序，如果长度小于47的话就用插入排序

### copyOf(T[] original,int newLength)  复制指定的数组
### copyOfRange(T[] original,int from,int to) 将指定数组的指定范围复制到一个新数组

#### //复制指定的数组（针对boolean类型）
    public static boolean[] copyOf(boolean[] original,int newLength)
     boolean[] copy = new boolean[newLength];
     //复制数组
     System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength));
     return copy;
 
#### //复制指定的数组（针对byte类型）
    public static byte[] copyOf(byte[] original,int newLength)
 
####  //复制指定的数组（针对char类型）
    public static char[] copyOf(char[] original, int newLength)
 
#### //复制指定的数组（针对double类型）
    public static double[] copyOf(double[] original, int newLength)
 
####  //复制指定的数组（针对float类型）
    public static float[] copyOf(float[] original, int newLength)
 
####  //复制指定的数组（针对int类型）
    public static int[] copyOf(int[] original, int newLength)
 
####  //复制指定的数组（针对long类型）
    public static long[] copyOf(long[] original,int newLength)
 
####  //复制指定的数组（针对short类型）
    public static short[] copyOf(short[] original,int newLength)
 
####  //复制指定的数组（针对T类型）
    public static <T> T[] copyOf(T[] original, int newLength)
 
####  //复制指定的数组（针对T类型）
    public static <T,U> T[] copyOf(U[] original, int newLength,Class<? extends T[]> newType)