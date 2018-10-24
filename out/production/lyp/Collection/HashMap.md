## 简介
### 定义
####（1）基于哈希表的Map接口实现类
####（2）继承了AbstractMap抽象类
####（3）实现可map接口 拥有一组map通用的操作
####（4）实现了Cloneable接口  可以进行拷贝 HashMap实现的是浅层拷贝 即对拷贝堆心啊哥的改变会影响拷贝的对象
####（5）实现了serializable接口  表示可实现序列化  可将HashMap对象保存至本地，之后可恢复状态
### 容量和HashMap大小
    数组的大小称为容量
    HashMap大小 = 数组长度 = 所有链表长度
### 加载因子 加载因子(Load factor)：HashMap在其容量自动增加前可达到多满的一种尺度
    a. 加载因子越大、填满的元素越多 = 空间利用率高、但冲突的机会加大、查找效率变低（因为链表变长了）
    b. 加载因子越小、填满的元素越少 = 空间利用率小、冲突的机会减小、查找效率高（链表不长）
      很多空间还没有利用就开始扩容 空间利用率很低 链表不会过长查询效率提高冲突概率减小
      但是频繁扩容导致耗费性能
    建议：设备空间内存充足，则可以选择空间效率换时间效率的策略 通过设置小的家在银子从而提高查询效率
         设备空间内存不足，则可以选择时间效率换空间效率的策略 通过设置大的家在因子从而牺牲查询速度 来换取更大的使用空间
### 数组长度 设置为2的幂
    （1）使得h & (length-1) = h % length
    因为取余的速效率低  为了提高效率采取位运算 &  
    （2）保证了数组长度一定数偶数，h & (length-1)的结果可能是奇数也可能是偶数，保证了存储的随机分布。
        如果数组长度是奇数h & (length-1) 结果一定是0，也就是存储位置一定是偶数。
### 特点
####（1）允许键值为空对象
####（2）非线程安全
####（3）不保证有序（如插入顺序）、也不保证不随时间变化

## 源码解析
### 构造函数
##### 1.先来看几个常量
    /*** 初始容量为16。容量必须是2的指数倍*/
    static final int DEFAULT_INITIAL_CAPACITY = 16;   
    /*** 最大容量*/
    static final int MAXIMUM_CAPACITY = 1 << 30;  
  
    /*** 加载因子默认是0.75.*/
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    平衡因子的作用在于，当存储的容量超过阈值（存储容量和加载因子的乘积）时，要对哈希表进行扩展操作。这个平衡因子的默认数值是JDK约定的。

    /*** 存储键值对对应的Entry数组*/
    transient Entry<K,V>[] table;  

    /** 键值对的个数/
    transient int size;

    /*** 表示一个阈值，当size超过了threshold就会扩容*/
        int threshold;
    /* 加载因子/
    final float loadFactor;
        
    / map结构修改次数，累加/
    transient int modCount;
        
    /** 默认阈值*/
    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;
        
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
        }
  
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }
   
    //最大容量 2的30次方
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认的加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //哈希桶，存放链表。 长度是2的N次方，或者初始化时为0.
    transient Node<K,V>[] table;

    //加载因子，用于计算哈希表元素数量的阈值。  threshold = 哈希桶.length * loadFactor;
    final float loadFactor;
        //哈希表内元素数量的阈值，当哈希表内元素数量超过阈值时，会发生扩容resize()。
        int threshold;

    public HashMap() {
        //默认构造函数，赋值加载因子为默认的0.75f
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
        }
    public HashMap(int initialCapacity) {
        //指定初始化容量的构造函数
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }
    //同时指定初始化容量 以及 加载因子， 用的很少，一般不会修改loadFactor
    public HashMap(int initialCapacity, float loadFactor) {
        //边界处理
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
        this.loadFactor = loadFactor;
        //设置阈值为  》=初始化容量的 2的n次方的值
        this.threshold = tableSizeFor(initialCapacity);
        }
    //新建一个哈希表，同时将另一个map m 里的所有元素加入表中
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
        }

    /*
    * 1. 通过key的hash值确定table下标
    * 2. 查找table下标，如果key存在则更新对应的value
    * 3. 如果key不存在则调用addEntry()方法
    */
    
    1.8
    /** 函数使用原型*/
      Map<String,Integer> map = new HashMap<String,Integer>();
    
     /**
       * 源码分析：主要是HashMap的构造函数 = 4个
       * 仅贴出关于HashMap构造函数的源码
       */
    
    public class HashMap<K,V>
        extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable{
    
        // 省略上节阐述的参数
    
      /**
         * 构造函数1：默认构造函数（无参）
         * 加载因子 & 容量 = 默认 = 0.75、16
         */
        public HashMap() {
            this.loadFactor = DEFAULT_LOAD_FACTOR;
        }
    
    /*** 构造函数2：指定“容量大小”的构造函数=* 加载因子 = 默认 = 0.75 、容量 = 指定大小*/
        public HashMap(int initialCapacity) {
            // 实际上是调用指定“容量大小”和“加载因子”的构造函数
            // 只是在传入的加载因子参数 = 默认加载因子
            this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }
    
    /** 构造函数3：指定“容量大小”和“加载因子”的构造函数  加载因子 & 容量 = 自己指定 */
        public HashMap(int initialCapacity, float loadFactor) {
    
            // 指定初始容量必须非负，否则报错  
             if (initialCapacity < 0)  
               throw new IllegalArgumentException("Illegal initial capacity: " +  
                                               initialCapacity); 
    
            // HashMap的最大容量只能是MAXIMUM_CAPACITY，哪怕传入的 > 最大容量
            if (initialCapacity > MAXIMUM_CAPACITY)
                initialCapacity = MAXIMUM_CAPACITY;
    
            // 填充比必须为正  
            if (loadFactor <= 0 || Float.isNaN(loadFactor))  
                throw new IllegalArgumentException("Illegal load factor: " +  
                                               loadFactor);  
            // 设置 加载因子
            this.loadFactor = loadFactor;
    
            // 设置 扩容阈值
            // 注：此处不是真正的阈值，仅仅只是将传入的容量大小转化为：>传入容量大小的最小的2的幂，该阈值后面会重新计算
            // 下面会详细讲解 ->> 分析1
            this.threshold = tableSizeFor(initialCapacity); 
    
        }
    
    /** 构造函数4：包含“子Map”的构造函数 即 构造出来的HashMap包含传入Map的映射关系 加载因子 & 容量 = 默认 */
    
        public HashMap(Map<? extends K, ? extends V> m) {
    
            // 设置容量大小 & 加载因子 = 默认
            this.loadFactor = DEFAULT_LOAD_FACTOR; 
    
            // 将传入的子Map中的全部元素逐个添加到HashMap中
            putMapEntries(m, false); 
        }
    }
    
       /**
         * 分析1：tableSizeFor(initialCapacity)
         * 作用：将传入的容量大小转化为：>传入容量大小的最小的2的幂
         * 与JDK 1.7对比：类似于JDK 1.7 中 inflateTable()里的 roundUpToPowerOf2(toSize)
         */
        static final int tableSizeFor(int cap) {
         int n = cap - 1;
         n |= n >>> 1;
         n |= n >>> 2;
         n |= n >>> 4;
         n |= n >>> 8;
         n |= n >>> 16;
         return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    
### JDK1.7

    // 1. 容量（capacity）： HashMap中数组的长度
    // a. 容量范围：必须是2的幂 & <最大容量（2的30次方）
    // b. 初始容量 = 哈希表创建时的容量
      // 默认容量 = 16 = 1<<4 = 00001中的1向左移4位 = 10000 = 十进制的2^4=16
      static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
      // 最大容量 =  2的30次方（若传入的容量过大，将被最大值替换）
      static final int MAXIMUM_CAPACITY = 1 << 30;
    
    // 2. 加载因子(Load factor)：HashMap在其容量自动增加前可达到多满的一种尺度
    // a. 加载因子越大、填满的元素越多 = 空间利用率高、但冲突的机会加大、查找效率变低（因为链表变长了）
    // b. 加载因子越小、填满的元素越少 = 空间利用率小、冲突的机会减小、查找效率高（链表不长）
      // 实际加载因子
      final float loadFactor;
      // 默认加载因子 = 0.75
      static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    // 3. 扩容阈值（threshold）：当哈希表的大小 ≥ 扩容阈值时，就会扩容哈希表（即扩充HashMap的容量） 
    // a. 扩容 = 对哈希表进行resize操作（即重建内部数据结构），从而哈希表将具有大约两倍的桶数
    // b. 扩容阈值 = 容量 x 加载因子
      int threshold;
    
    // 4. 其他
     // 存储数据的Entry类型 数组，长度 = 2的幂
     // HashMap的实现方式 = 拉链法，Entry数组上的每个元素本质上是一个单向链表
      transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;  
     // HashMap的大小，即 HashMap中存储的键值对的数量
      transient int size;

    /*** 函数使用原型*/
    Map<String,Integer> map = new HashMap<String,Integer>();

    /*** 源码分析：主要是HashMap的构造函数 = 4个* 仅贴出关于HashMap构造函数的源码 */
    public class HashMap<K,V>
      extends AbstractMap<K,V>
      implements Map<K,V>, Cloneable, Serializable{

    // 省略上节阐述的参数

    /**
     * 构造函数1：默认构造函数（无参）
     * 加载因子 & 容量 = 默认 = 0.75、16
     */
    public HashMap() {
        // 实际上是调用构造函数3：指定“容量大小”和“加载因子”的构造函数
        // 传入的指定容量 & 加载因子 = 默认
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); 
    }

    /**
     * 构造函数2：指定“容量大小”的构造函数
     * 加载因子 = 默认 = 0.75 、容量 = 指定大小
     */
    public HashMap(int initialCapacity) {
        // 实际上是调用指定“容量大小”和“加载因子”的构造函数
        // 只是在传入的加载因子参数 = 默认加载因子
        this(initialCapacity, DEFAULT_LOAD_FACTOR);

    }

    /**
     * 构造函数3：指定“容量大小”和“加载因子”的构造函数
     * 加载因子 & 容量 = 自己指定
     */
    public HashMap(int initialCapacity, float loadFactor) {

        // HashMap的最大容量只能是MAXIMUM_CAPACITY，哪怕传入的 > 最大容量
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        // 设置 加载因子
        this.loadFactor = loadFactor;
        // 设置 扩容阈值 = 初始容量
        // 注：此处不是真正的阈值，是为了扩展table，该阈值后面会重新计算，下面会详细讲解  
        threshold = initialCapacity;   

        init(); // 一个空方法用于未来的子对象扩展
    }

    /**
     * 构造函数4：包含“子Map”的构造函数
     * 即 构造出来的HashMap包含传入Map的映射关系
     * 加载因子 & 容量 = 默认
     */

    public HashMap(Map<? extends K, ? extends V> m) {

        // 设置容量大小 & 加载因子 = 默认
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);

        // 该方法用于初始化 数组 & 阈值，下面会详细说明
        inflateTable(threshold);

        // 将传入的子Map中的全部元素逐个添加到HashMap中
        putAllForCreate(m);
    }
    }

### // 静态内部类
    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next; // 只想下一个entry节点
        int hash;

        /**
         * 构造函数，每次都用新的节点指向链表的头结点。新节点作为链表新的头结点
         */
        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n; //
            key = k;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return (key==null   ? 0 : key.hashCode()) ^
                   (value==null ? 0 : value.hashCode());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        /**
         * This method is invoked whenever the value in an entry is
         * overwritten by an invocation of put(k,v) for a key k that's already
         * in the HashMap.
         */
        void recordAccess(HashMap<K,V> m) {
        }

        /**
         * This method is invoked whenever the entry is
         * removed from the table.
         */
        void recordRemoval(HashMap<K,V> m) {
        }
    }


### createEntry（）
    void createEntry(int hash, K key, V value, int bucketIndex) {
         Entry<K,V> e = table[bucketIndex];
         table[bucketIndex] = new Entry<>(hash, key, value, e);
         size++;
     }
### putForCreate 此方法用在构造函数、克隆或者反序列化的时候调用。不会调整table数组的大小。
    private void putForCreate(K key, V value) {
    int hash = null == key ? 0 : hash(key);
    int i = indexFor(hash, table.length);
    /**
     * Look for preexisting entry for key.  This will never happen for
     * clone or deserialize.  It will only happen for construction if the
     * input Map is a sorted map whose ordering is inconsistent w/ equals.
     */
    //这里的处理方式是如果遇到重复的节点就覆盖
      for (Entry<K,V> e = table[i]; e != null; e = e.next) { 
        Object k;
        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k)))) {
            e.value = value;
            return;
        }
    }
    createEntry(hash, key, value, i);
    }

### put  真正初始化哈希表（初始化存储数组table）是在第1次添加键值对时，即第1次调用put（）时
    public V put(K key, V value) {
    
    （分析1）// 1. 若 哈希表未初始化（即 table为空) 
            // 则使用 构造函数时设置的阈值(即初始容量) 初始化 数组table  
            if (table == EMPTY_TABLE) { 
            inflateTable(threshold); 
        }  
     注意：由此处可以看出只能由一个key==Null的键值对  
    （分析2）  
      2. 判断key是否为空值null
       2.1 若key == null，则将该键-值 存放到数组table 中的第1个位置，即table [0]
        // （本质：key = Null时，hash值 = 0，故存放到table[0]中）
        // 该位置永远只有1个value，新传进来的value会覆盖旧的value
        if (key == null)
        return putForNullKey(value);
        // 2.2 若 key ≠ null，则计算存放数组 table 中的位置（下标、索引）
        // a. 根据键值key计算hash值
        int hash = hash(key); // 重点!!!
        // b. 根据hash值 最终获得 key对应存放的数组Table中位置
        int i = indexFor(hash, table.length); // 查找对应的数组下标  注意这里是hash值
    （分析3）
        3. 判断该key对应的值是否已存在（通过遍历 以该数组元素为头结点的链表 逐个判断）
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        // 3.1 若该key已存在（即 key-value已存在 ），则用 新value 替换 旧value
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
        V oldValue = e.value;
        e.value = value;
        e.recordAccess(this);
        return oldValue;
        }
        }
        // 没有在相同hash值的链表中找到key相同的节点  没有相同的就添加一个新的节点
        modCount++;
        // 3.2 若 该key不存在，则将“key-value”添加到table中
        addEntry(hash, key, value, i); // 在i位置对应的链表上添加一个节点
        return null;
        }
        
### inflateTable（）       
      /*** 函数使用原型*/
              if (table == EMPTY_TABLE) { 
                inflateTable(threshold); 
            }  
        
      /*** 源码分析：inflateTable(threshold); */
             private void inflateTable(int toSize) {  
         // 1. 将传入的容量大小转化为：>传入容量大小的最小的2的次幂
            // 即如果传入的是容量大小是19，那么转化后，初始化容量大小为32（即2的5次幂）
           int capacity = roundUpToPowerOf2(toSize);->>分析1   
        
        // 2. 重新计算阈值 threshold = 容量 * 加载因子  
            threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);  
        
        // 3. 使用计算后的初始容量（已经是2的次幂） 初始化数组table（作为数组长度）
           // 即 哈希表的容量大小 = 数组大小（长度）
           table = new Entry[capacity]; //用该容量初始化table  
           initHashSeedAsNeeded(capacity);  
        }  
        
### roundUpToPowerOf2（）     
      /**分析1：roundUpToPowerOf2(toSize)
       * 作用：将传入的容量大小转化为：>传入容量大小的最小的2的幂
       * 特别注意：容量大小必须为2的幂，该原因在下面的讲解会详细分析
       */
        
       private static int roundUpToPowerOf2(int number) {  
         //若 容量超过了最大值，初始化容量设置为最大值 ；否则，设置为：>传入容量大小的最小的2的次幂
         return number >= MAXIMUM_CAPACITY  ? 
              MAXIMUM_CAPACITY  : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;

### putForNullKey
     private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) { // 寻找数组0位置对应的链表key值为null的节点，进而更新value值
        if (e.key == null) {
        V oldValue = e.value;
        e.value = value;
        e.recordAccess(this);
        return oldValue;
        }
        }
        modCount++;
        // 2 .若无key==null的键，那么调用addEntry（），将空键 & 对应的值封装到Entry中，并放到table[0]中
        addEntry(0, null, value, 0); // 在数组0位置对应的链表上添加一个节点
        return null;
        }
        // 注：
            // a. addEntry（）的第1个参数 = hash值 = 传入0
            // b. 即 说明：当key = null时，也有hash值 = 0，所以HashMap的key 可为null
            // c. 对比HashTable，由于HashTable对key直接hashCode（），若key为null时，会抛出异常，所以HashTable的key不可为null
            // d. 此处只需知道是将 key-value 添加到HashMap中即可，关于addEntry（）的源码分析将等到下面再详细说明，

### addEntry     
     void addEntry(int hash, K key, V value, int bucketIndex) {
     // 参数3 = 插入数组table的索引位置 = 数组下标
     
     // 1. 插入前，先判断容量是否足够
               // 1.1 若不足够，则进行扩容（2倍）、重新计算Hash值、重新计算存储数组下标
     if ((size >= threshold) && (null != table[bucketIndex])) { // 如果数据大小已经超过阈值并且数组对应的bucket不为空，则需要扩容
         resize(2 * table.length); // 扩容  2倍扩容
         hash = (null != key) ? hash(key) : 0; // key为null的时，hash值设为0
         bucketIndex = indexFor(hash, table.length); // 确定是哪一个链表（bucket下标）
     }  
     // 1.2 若容量足够，则创建1个新的数组元素（Entry） 并放入到数组中--> 分析2
    createEntry(hash, key, value, bucketIndex);
    }

### 扩容 resize（） java 默认2倍扩容
    void resize(int newCapacity) {
       Entry[] oldTable = table;   //保存旧数组
       
       int oldCapacity = oldTable.length;  //保存旧容量，即数组长度
       
       // 3. 若旧容量已经是系统默认最大容量了，那么将阈值设置成整型的最大值，退出
       if (oldCapacity == MAXIMUM_CAPACITY) { //当当前数据长度已经达到最大容量
           threshold = Integer.MAX_VALUE;
           return;
       }
    
       // 4. 根据新容量（2倍容量）新建1个数组，即新table  
       Entry[] newTable = new Entry[newCapacity]; // 创建新的数组
       boolean oldAltHashing = useAltHashing;
       useAltHashing |= sun.misc.VM.isBooted() &&
               (newCapacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
       boolean rehash = oldAltHashing ^ useAltHashing; // 是否需要重新计算hash值
       transfer(newTable, rehash);  // 将table的数据转移到新的table中
       table = newTable; // 数组重新赋值
       threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1); //重新计算阈值
    }
### get（）
    /*** 根据key得到对应的value值*/
    public V get(Object key) {
      if (key == null)
          return getForNullKey(); // 如果key为null，则调用getForNullKey()
      Entry<K,V> entry = getEntry(key); // 重点在getEntry()函数
       return null == entry ? null : entry.getValue();
    }
     
### getForNullKey（）
    /*** 得到 key = null 对应的value值*/
    private V getForNullKey() {
     for (Entry<K,V> e = table[0]; e != null; e = e.next) {  //从这个方法可以看出只能put进去一个空值
       if (e.key == null)
           return e.value;
    }
       return null;
    }
    
### getEntry（）    
     /*** 根据key得到对应的Entry对象*/
        final Entry<K,V> getEntry(Object key) {
            int hash = (key == null) ? 0 : hash(key);
            for (Entry<K,V> e = table[indexFor(hash, table.length)];
                 e != null;
                 e = e.next) { // 遍历hash值相同（通过key得到）的那个bucket（链表）
                Object k;
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
            return null;
     }
    

### clear（）
    public void clear() {
        modCount++; // +1
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++)
            tab[i] = null;
        size = 0; // 置0
    }
    
        }
        
### transfer 转移数据
        void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
            for (Entry<K,V> e : table) { //遍历旧数组
                while(null != e) { //将这一个链表遍历添加到新的数组对应的bucket中
                    Entry<K,V> next = e.next;
                    if (rehash) {
                        e.hash = null == e.key ? 0 : hash(e.key);
                    }
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                }
            }
        }
        
### indexFor（）        
    /*** 得到hash值在table数组中的位置*/
        static int indexFor(int h, int length) {
            return h & (length-1);
        }
    
### JDK 1.7 和 1.8 的不同  
    /函数使用原型 主要分为2步：计算hash值、根据hash值再计算得出最后数组位置/
        // a. 根据键值key计算hash值 ->> 分析1
        int hash = hash(key);
        // b. 根据hash值 最终获得 key对应存放的数组Table中位置 ->> 分析2
        int i = indexFor(hash, table.length);

     /* 源码分析1：hash(key) 
     *该函数在JDK 1.7 和 1.8 中的实现不同，但原理一样 = 扰动函数 = 使得根据key生成的哈希码（hash值）分布更加均匀、更具备随机性，避免出现hash值冲突（即指不同key但生成同1个hash值）
     * JDK 1.7 做了9次扰动处理 = 4次位运算 + 5次异或运算
     * JDK 1.8 简化了扰动函数 = 只做了2次扰动 = 1次位运算 + 1次异或运算
     */

    // JDK 1.7实现：将 键key 转换成 哈希码（hash值）操作  = 使用hashCode() + 4次位运算 + 5次异或运算（9次扰动）
    static final int hash(int h) {
      h ^= k.hashCode(); 
      h ^= (h >>> 20) ^ (h >>> 12);
      return h ^ (h >>> 7) ^ (h >>> 4);
    }

    // JDK 1.8实现：将 键key 转换成 哈希码（hash值）操作 = 使用hashCode() + 1次位运算 + 1次异或运算（2次扰动）
    // 1. 取hashCode值： h = key.hashCode() 
    //  2. 高位参与低位的运算：h ^ (h >>> 16)  
    static final int hash(Object key) {
       int h;
       return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
       // a. 当key = null时，hash值 = 0，所以HashMap的key 可为null      
       // 注：对比HashTable，HashTable对key直接hashCode（），若key为null时，会抛出异常，所以HashTable的key不可为null
       // b. 当key ≠ null时，则通过先计算出 key的 hashCode()（记为h），然后 对哈希码进行 扰动处理： 按位 异或（^） 哈希码自身右移16位后的二进制
    }

    /**函数源码分析2：indexFor(hash, table.length)
     * JDK 1.8中实际上无该函数，但原理相同，即具备类似作用的函数
     */
      static int indexFor(int h, int length) {  
          return h & (length-1); 
          // 将对哈希码扰动处理后的结果 与运算(&) （数组长度-1），最终得到存储在数组table的位置（即数组下标、索引）
    }
    
### remove（）
    public V remove(Object key) {
        Entry<K,V> e = removeEntryForKey(key); // ！！！
        return (e == null ? null : e.value); // 返回key对应的value值
    }
    
### removeEntryForKey（）    
    final Entry<K,V> removeEntryForKey(Object key) {
        int hash = (key == null) ? 0 : hash(key); //计算hash值
        int i = indexFor(hash, table.length); // 得到下标
        Entry<K,V> prev = table[i];
        Entry<K,V> e = prev;
        while (e != null) {
            Entry<K,V> next = e.next;
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k)))) {
                modCount++; // 操作次数+1
                size--;
                if (prev == e) // 如果是buckek的头节点是要找的结点，直接将数组指向next
                    table[i] = next;
                else
                    prev.next = next;
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }
        return e; // 如果链表遍历结束还是没找到，则此时e为null，返回null
    }

### 问题解答
####（1）为什么不直接采用经过hashCode（）处理的哈希码 作为 存储数组table的下标位置？
    结论：容易出现 哈希码 与 数组大小范围不匹配的情况，即 计算出来的哈希码可能 不在数组大小范围内，从而导致无法匹配存储位置
    哈希吗一般是整型，二进制的32位大夫海的int范围 = 2^31 ~ 2 ^ 31 -1 约为40亿的映射空间
    HashMap的容量范围（数组大小）最小值：初始默认大小 = 16
                             最大值：2^30 若设置的容量过大，将被最大值替换
    冲突: hashMap的容量范围一般不会取到最大值
    从而导致算出来的哈希码可能不再数组大小范围内，从而导致无法匹配存储位置
    为了解决 “哈希码与数组大小范围不匹配” 的问题，HashMap给出了解决方案：哈希码 与运算（&） （数组长度-1）
    
####（2）为什么采用 哈希码 与运算(&) （数组长度-1） 计算数组下标？
    结论：根据HashMap的容量大小（数组长度），按需取 哈希码一定数量的低位 作为存储的数组下标位置，从而 解决 “哈希码与数组大小范围不匹配” 的问题
####（3）为什么在计算数组下标前，需对哈希码进行二次处理：扰动处理？
    结论：加大哈希码低位的随机性，使得分布更均匀，从而提高对应数组存储下标位置的随机性 & 均匀性，最终减少Hash冲突
    让哈希码分布的更加均匀 从而避免出现哈希冲突

### 分析4：若对应的key已存在，则 使用 新value 替换 旧value
    注：当发生 Hash冲突时，为了保证 键key的唯一性哈希表并不会马上在链表中插入新数据，而是先查找该 key是否已存在，若已存在，则替换即可
    
## 参考： https://blog.csdn.net/carson_ho/article/details/79373026