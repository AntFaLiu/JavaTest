package myCollection;

import java.util.Iterator;

interface List<T> {
    void add(T value);

    void remove(T value);

    interface Entry<T> {
        Entry<T> getNext();

        void setNext(Entry<T> next);

        T getValue();

        void setValue(T value);
    }
}

public class MyLinkedListTest {

    public static void main(String[] args) {
        List l = new MyLinkedList();
        for(int i = 0;i < 100;i++){
            l.add(i);
        }
        MyLinkedList.MyIterator iterator = ((MyLinkedList) l).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class MyLinkedList<T> implements List<T> {
    private Entry<T> head; //头结点

    //加一个Last指向尾 尾节点
    public MyLinkedList() {
        // TODO Auto-generated constructor stub
        head = new Entry<T>();
    }

    @Override
    public void add(T value) {
        // TODO Auto-generated method stub
        Entry<T> entry = new Entry<T>(value);
        entry.next = head.next;
        head.next = entry;
    }

    private Entry<T> getPreEntry(T value) {
        for (Entry<T> p = head; p.next != null; p = p.next) {
            if (p.next.value.equals(value)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void remove(T value) {
        // TODO Auto-generated method stub
        Entry<T> preEntry = getPreEntry(value);
        if (preEntry == null)
            return;
        Entry<T> temp = preEntry.next;
        preEntry.next = preEntry.next.next;
        temp.value = null;
        temp.next = null;
    }

    public MyIterator iterator() {
        // TODO Auto-generated method stub
        return new MyIterator();
    }

    static class Entry<T> implements List.Entry<T> {
        private T value;
        private Entry<T> next;

        //加一个后继指针
        public Entry() {//带头节点
            next = null;
        }

        public Entry(T value) {//普通节点类型
            this.value = value;
            next = null;
        }

        @Override
        public Entry<T> getNext() {
            // TODO Auto-generated method stub
            return next;
        }

        @Override
        public void setNext(List.Entry<T> next) {
            // TODO Auto-generated method stub
            this.next = (Entry<T>) next;
        }

        @Override
        public T getValue() {
            // TODO Auto-generated method stub
            return value;
        }

        @Override
        public void setValue(T value) {
            // TODO Auto-generated method stub
            this.value = value;
        }

    }

    class MyIterator implements Iterator<T> {
        /*迭代器遍历通过结点类型的引用*/
        private Entry<T> entry = head.next;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return entry != null;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            T value = entry.getValue();
            entry = entry.getNext();
            return value;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            MyLinkedList.this.remove(next());
        }
    }
}
