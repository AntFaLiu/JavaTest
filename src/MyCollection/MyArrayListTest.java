package MyCollection;

import java.util.Arrays;
import java.util.Iterator;

class MyArrayList<T> implements Iterable<T> {
    private T[] element;
    private int size;

    public MyArrayList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int n) {
        element = (T[]) new Object[n];
        size = 0;
    }

    private boolean isFull() {
        return size == element.length;
    }

    private void expend() {
        element = Arrays.copyOf(element, element.length + element.length >> 1);
    }

    public void add(T elem) {
        if (isFull()) {
            expend();
        }
        element[size++] = elem;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void remove(T value) {
        if (isEmpty()) {
            return;
        }
        element[size] = null;
        size--;
    }

    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        /*返回自定义迭代器对象*/
        return new MyIterator();
    }

    /*自定义迭代器对象实现Iterator接口*/
    class MyIterator implements Iterator<T> {
        /*遍历数组通过下标进行遍历，所以迭代器实例变量为下标*/
        private int index = 0;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return index < size;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            T val = MyArrayList.this.element[index];
            index++;
            return val;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            MyArrayList.this.remove(MyArrayList.this.element[index]);
            element[index] = null;
            size--;
        }
    }
}

public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add(5);
        Iterator iterator = list.iterator();
        System.out.println();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
