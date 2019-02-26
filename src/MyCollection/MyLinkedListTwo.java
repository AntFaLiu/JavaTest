package myCollection;

import java.util.Iterator;

public class MyLinkedListTwo {

    public static void main(String[] args) {
        List l = new MyLinkedListDou();
        for (int i = 0; i < 100; i++) {
            l.add(i);
        }
        System.out.println("size: "+((MyLinkedListDou) l).size);
        MyLinkedListDou.MyIterator iterator = ((MyLinkedListDou) l).iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        l.remove(21);
        System.out.println("size: "+((MyLinkedListDou) l).size);
        iterator = ((MyLinkedListDou) l).iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

    }
}

class MyLinkedListDou<T> implements List<T> {
    int size;
    private Entry<T> head; //头结点
    private Entry<T> last; //尾结点

    //加一个Last指向尾 尾节点
    public MyLinkedListDou() {
        // TODO Auto-generated constructor stub
        head = new Entry<T>();
        last = head;
    }

    @Override
    public void add(T value) {   //尾插法
        // TODO Auto-generated method stub
        Entry<T> entry = new Entry<T>(value);
        entry.pre = last;  //尾
        last.next = entry;
        last = entry;
        size++;
    }

    private Entry<T> getEntry(T value) {  //因为是双向链表所以不用找前驱
        for (Entry<T> p = head.next; p != null; p = p.next) {
            if (p.value.equals(value)) {
                return p;
            }
        }
        return null;
    }

    public void set(int index,int value){


    }


    @Override
    public void remove(T value) {
        // TODO Auto-generated method stub
        Entry<T> preEntry = getEntry(value);
        if (preEntry == null)
            return;
        final Entry<T> next = preEntry.next;
        final Entry<T> prev = preEntry.pre;
        if (prev == null) {   //删除的就是第一个节点
            head = next;
        } else {   //除去第一个节点的任意节点
            prev.next = next;
            preEntry.pre = null;
        }

        if (next == null) {//删除的就是最后一个节点
            last = prev;
        } else {  //除去最后一个节点的任意节点
            next.pre = prev;
            preEntry.next = null;
        }

        preEntry.value = null;
        size--;
    }

    public MyIterator iterator() {
        // TODO Auto-generated method stub
        return new MyIterator();
    }

    static class Entry<T> implements List.Entry<T> {
        private T value;

        private Entry<T> next;

        private Entry<T> pre;

        public Entry() {
            this(null, null, null);
        }

        //加一个后继指针
        public Entry(T value) {//带头节点
            this(value, null, null);
        }

        public Entry(T value, Entry<T> next, Entry<T> pre) {       //普通节点类型
            this.value = value;
            next = null;
            pre = null;
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

        public Entry<T> getPre() {
            return pre;
        }

        public void setPre(Entry<T> pre) {
            this.pre = pre;
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
            MyLinkedListDou.this.remove(next());
        }
    }
}

