package MyCollection;

import java.util.Map;

public class MyHashMapTest {
    public static void main(String[] args) {

    }
}

class MyHashMap<K, V> implements Map.Entry<K, V> {

    final int DEFAULT_INITIAL_CAPACITY = 16;

    final int MAXIMUM_CAPACITY = 1000;

    Entry<K, V>[] table;

    int size;  //数组内的元素个数

    public int getDEFAULT_INITIAL_CAPACITY() {
        return DEFAULT_INITIAL_CAPACITY;
    }

    public int getMAXIMUM_CAPACITY() {
        return MAXIMUM_CAPACITY;
    }

    public Entry<K, V>[] getTable() {
        return table;
    }

    public void setTable(Entry<K, V>[] table) {
        this.table = table;
    }

    public V put(K key, V value) {  //头插法

        int index = hash(key);
        //i位置已经有数据了，往链表添加元素
        for (Entry e = table[index]; e != null; e = e.next) {
            //且数组中有这个key,覆盖其value
            if (e.key == key || key.equals(e.key)) {
                V oldValue = (V)e.value;
                e.value = value;
                //返回oldValue
                return oldValue;
            }
        }
        //如果i位置没有数据，或i位置有数据，但key是新的key,新增节点
        addEntry(key, value, key.hashCode(), index);
        return null;
    }

    private void addEntry(Object key, Object value, int hashValue, int i) {
        //如果超过了原数组大小，则扩大数组
        if (++size == table.length) {
            Entry[] newTable = new Entry[table.length * 2];
            System.arraycopy(table, 0, newTable, 0, table.length);
            table = newTable;
        }
        //的到i位置的数据
        Entry eNode = table[i];
        //新增节点，将该节点的next指向前一个节点
        table[i] = new Entry<>(hashValue, key, value, eNode);
    }

    private int hash(K key) {
        int hash = key.hashCode();
        return hash % table.length;
    }

    public V remove(Object key) {
        return null;
    }

    @Override
    public K getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }

    @Override
    public V setValue(V value) {
        return null;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;
        Entry next;
        V value;
        int hash;

        Entry(int h, K k, V v, Entry<K, V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

//        public void setValue(V value) {
//            this.value = value;
//        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }
}