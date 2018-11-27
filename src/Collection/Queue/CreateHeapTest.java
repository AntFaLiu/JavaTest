package Collection.Queue;

import java.util.Arrays;

public class CreateHeapTest {
    public static void Heap_Adjust(int[] arr,int start,int end)
    {
        int tmp = arr[start];
        int parent = start;//start 父节点

        for(int i = 2*start+1;i < end;i=i*2+1)
        {
            if(i+1<=end && arr[i]<arr[i+1])//求左右子树中较大的
            {
                i++;
            }
            if(arr[i] > tmp)
            {
                arr[parent] = arr[i];
                parent = i;
            }else{
                break;
            }
        }
        arr[parent] = tmp;
    }

    public static void Heap_Sort(int[] arr) {
        int i = 0;
        for (i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            Heap_Adjust(arr, i, arr.length - 1);    //构建大根堆   就是根节点是最大的数
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,6,16,10,13,14,18,17,25,5};
        Heap_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
