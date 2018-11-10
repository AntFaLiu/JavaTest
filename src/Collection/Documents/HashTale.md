## 构造方法
    Hashtable 一共提供了 4 个构造方法：
    public Hashtable(int initialCapacity, float loadFactor)： 用指定初始容量和指定加载因子构造一个新的空哈希表。useAltHashing 为 boolean，其如果为真，则执行另一散列的字符串键，以减少由于弱哈希计算导致的哈希冲突的发生。 
    public Hashtable(int initialCapacity)：用指定初始容量和默认的加载因子 (0.75) 构造一个新的空哈希表。 
    public Hashtable()：默认构造函数，容量为 11，加载因子为 0.75。 
    public Hashtable(Map< ? extends K, ? extends V> t)：构造一个与给定的 Map 具有相同映射关系的新哈希表。
### public synchronized Enumeration<K> keys() {
        return this.<K>getEnumeration(KEYS);
    }
### public synchronized Enumeration<V> elements() {
            return this.<V>getEnumeration(VALUES);
        }
