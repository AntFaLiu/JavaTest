package MyCollection;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, String> m = new MyHashMap();
        m.put("lyp", "24");
        m.put("lisi", "25");
        m.put("zhangsan", "26");
        m.put("wangwu", "27");
        m.put("chenliu", "25");
        m.put("yangjiu", "56");
        m.put("heshi", "47");
        m.put("susan", "66");


        System.out.println(m.getValue("lyp"));
        Iterator<Map.Entry<String, String>> iterator = m.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
    }
}

class MyHashMap<K, V> implements Iterable {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final int MAXIMUM_CAPACITY = 1000;

    static final float DEFAULT_LOAD_FACTOR = (float) 0.75;

    static final Entry<?, ?>[] EMPTY_TABLE = {};

    Entry<K, V>[] table = (Entry<K, V>[]) EMPTY_TABLE;

    int size = 0;  //数组内的元素个数

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        //初始容量最大不能超过2的30次方
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        //显然加载因子不能为负数
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

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
        if (table.length == 0) {
            inflateTable();
        }
        int index = hash(key);
        //i位置已经有数据了，往链表添加元素
        for (Entry e = table[index]; e != null; e = e.next) {
            //且数组中有这个key,覆盖其value
            if (e.key == key || key.equals(e.key)) {
                V oldValue = (V) e.value;
                e.value = value;
                //返回oldValue
                return oldValue;
            }
        }
        //如果i位置没有数据，或i位置有数据，但key是新的key,新增节点
        addEntry(key, value, key.hashCode(), index);
        return null;
    }

    private void inflateTable() {
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
        System.out.println("table.length: " + table.length);
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
        int hash = key.hashCode() >>> 1;
        //System.out.println("key: " + key + "  hash:" + hash + "  table.length: " + table.length + "tthash%table.length: " + hash % table.length);
        return hash % table.length;
    }

    public V remove(K key) {
        Entry<K, V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    final Entry<K, V> removeEntryForKey(K key) {
        if (size == 0) {
            return null;
        }
        int index = (key == null) ? 0 : hash(key);
        Entry<K, V> prev = table[index];
        Entry<K, V> e = prev;

        while (e != null) {
            Entry<K, V> next = e.next;
            Object k;
            if (((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (prev == e)
                    table[index] = next;
                else
                    prev.next = next;
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }


    public V getValue(K key) {
        if (key == null)
            return getForNullKey();
        Entry<K, V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    private V getForNullKey() {
        if (size == 0) {
            return null;
        }
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null)
                return e.value;
        }
        return null;
    }

    final Entry<K, V> getEntry(K key) {
        if (size == 0) {
            return null;
        }

        int index = (key == null) ? 0 : hash(key);
        for (Entry<K, V> e = table[index];
             e != null;
             e = e.next) {
            Object k;
            if ((k = e.key) == key || (key != null && key.equals(k)))
                return e;
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return new EntryIterator();
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


        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K, V> next;        // next entry to return
        int index;              // current slot
        Entry<K, V> current;     // current entry

        HashIterator() {
            if (size > 0) { // advance to first entry
                Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<K, V> nextEntry() {
            Entry<K, V> e = next;
            if (e == null)
                throw new NoSuchElementException();

            if ((next = e.next) == null) {
                Entry[] t = table;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
            current = e;
            return e;
        }
    }

    private final class EntryIterator extends HashIterator<Map.Entry<K, V>> {
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }
}