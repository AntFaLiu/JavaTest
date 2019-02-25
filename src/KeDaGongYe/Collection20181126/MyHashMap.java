package keDaGongYe.Collection20181126;

public class MyHashMap {
    int size;
    Entry[] table; // 16
    int max = 12;

    public Entry[] grow() {
        //table.length ====>2*table.length
        return new Entry[2 * table.length];
    }

    public int hash(Integer key) {
        int index = key % table.length;
        return index;
    }

    public void transer(Entry[] newTable) {
        Entry[] oldTable = table;
        for (int i = 0; i < oldTable.length; i++) {
            Entry e = oldTable[i];
            for (; e != null; e = e.next) {
                int index = hash(e.key);
                //头插法插入  newTable
            }
        }
        table = newTable;

    }

    public void put(Integer key, Integer value) {
        int index = hash(key);
        Entry x = table[index];
        for (; x != null; x = x.next) {
            if (key.equals(x.key))
                return;
        }
        addEntry(key, value, index);
    }

    public void addEntry(Integer key, Integer value, int index) {
        new Entry();
        //单链表头插法
        if (size > max) {
            transer(grow());
        }
    }

    public void remove(Integer key) {
        int index = hash(key);
        //单链表的删除
    }

    static class Entry {
        Integer value;
        Integer key;
        Entry next;
    }
}
