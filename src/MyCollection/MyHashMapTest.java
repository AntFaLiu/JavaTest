package MyCollection;

public class MyHashMapTest {
    public static void main(String[] args) {

    }
}

class MyHashMap<K, V> {

    final int DEFAULT_INITIAL_CAPACITY = 16;

    final int MAXIMUM_CAPACITY = 1000;

    Entry<K, V>[] table;

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
        return null;
    }

    public V remove(Object key) {
        return null;
    }

    static class Entry<K, V> {
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

        public void setValue(V value) {
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }
}