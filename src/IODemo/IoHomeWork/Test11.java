package IODemo.IoHomeWork;

import java.io.*;
import java.util.Arrays;

/**
 * 自己实现序列化
 */

class MyArray implements Serializable {
    Integer[] arr = new Integer[10];
    int size = 5;

    public MyArray() {
        for (int i = 0; i < 5; i++) {
            arr[i] = i;
        }
    }

    public Integer[] getArr() {
        return arr;
    }

    public void setArr(Integer[] arr) {
        this.arr = arr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void writeObject(ObjectOutputStream s) throws IOException {
        s.writeInt(size);
        for (int i = 0; i < size; i++) {
            s.writeObject(arr[i]);
        }
    }

    public MyArray readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        MyArray m = new MyArray();
        int len = s.readInt();
        Integer[] a = new Integer[len];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                a[i] = (Integer) s.readObject();
            }
        }
        m.setArr(a);
        m.setSize(5);
        return m;
    }

    @Override
    public String toString() {
        return "Myarray" + "{ array: " + Arrays.toString(arr) + " ,size: " + String.valueOf(size) + "}";
    }
}

public class Test11 {
    public static void main(String[] args) {
        MyArray myArray = new MyArray();
        try {
            myArray.writeObject(new ObjectOutputStream(new FileOutputStream("tmpArray")));
            MyArray m = myArray.readObject(new ObjectInputStream(new FileInputStream("tmpArray")));
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
