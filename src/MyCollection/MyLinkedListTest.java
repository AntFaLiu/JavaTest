package MyCollection;

import java.util.Iterator;

public class MyLinkedListTest {

    public static void main(String[] args) {
        List l = new MyLinkedList();
        l.add(1);
        MyLinkedList.MyIterator iterator = ((MyLinkedList) l).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

interface List<T>{
    interface Entry<T>{
        Entry<T> getNext();
        T getValue();
        void setValue(T value);
        void setNext(Entry<T> next);
    }

    void add(T value);
    void remove(T value);
}
class MyLinkedList<T> implements List<T>{
    static class Entry<T> implements List.Entry<T>{
        private T value;
        private Entry<T> next;
        public Entry(){//带头节点
            next=null;
        }
        public Entry(T value){//普通节点类型
            this.value=value;
            next=null;
        }

        @Override
        public Entry<T> getNext() {
            // TODO Auto-generated method stub
            return next;
        }
        @Override
        public T getValue() {
            // TODO Auto-generated method stub
            return value;
        }
        @Override
        public void setValue(T value) {
            // TODO Auto-generated method stub
            this.value=value;
        }
        @Override
        public void setNext(List.Entry<T> next) {
            // TODO Auto-generated method stub
            this.next=(Entry<T>) next;
        }

    }

    private Entry<T> head;
    public MyLinkedList() {
        // TODO Auto-generated constructor stub
        head=new Entry<T>();
    }
    @Override
    public void add(T value) {
        // TODO Auto-generated method stub
        Entry<T> entry=new Entry<T>(value);
        entry.next=head.next;
        head.next=entry;
    }
    private Entry<T> getPreEntry(T value){
        for(Entry<T> p =head;p.next!=null;p=p.next){
            if(p.next.value.equals(value)){
                return p;
            }
        }
        return null;
    }
    @Override
    public void remove(T value) {
        // TODO Auto-generated method stub
        Entry<T> preEntry=getPreEntry(value);
        if(preEntry==null)
            return;
        Entry<T> temp=preEntry.next;
        preEntry.next=preEntry.next.next;
        temp.value=null;
    }
    public MyIterator iterator() {
        // TODO Auto-generated method stub
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {
        /*迭代器遍历通过结点类型的引用*/
        private Entry<T> entry=head.next;
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return entry!=null;
        }
        @Override
        public T next() {
            // TODO Auto-generated method stub
            T value=entry.getValue();
            entry=entry.getNext();
            return value;
        }
        @Override
        public void remove() {
            // TODO Auto-generated method stub
            MyLinkedList.this.remove(next());
        }
    }
}
