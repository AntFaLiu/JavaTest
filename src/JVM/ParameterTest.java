package JVM;

import java.util.Arrays;

public class ParameterTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 45, 63, 78};
        int[] brr = arr.clone();
        System.out.println("brr: " + Arrays.toString(arr));
        int i = 0;
        int j = 1;
        int k = 2;
        while(true){
            System.out.println(i++);
        }
        //System.out.println(System.getProperty("configurePath"));

    }
}
