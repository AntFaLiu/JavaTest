# Java 集合框架
   早在 Java 2 中之前，Java 就提供了特设类。比如：Dictionary, Vector, Stack, 和 Properties 这些类用来存储和操作对象组。
虽然这些类都非常有用，但是它们缺少一个核心的，统一的主题。由于这个原因，使用 Vector 类的方式和使用 Properties 类的方式有着很大不同。
集合框架被设计成要满足以下几个目标。
该框架必须是高性能的。基本集合（动态数组，链表，树，哈希表）的实现也必须是高效的。
该框架允许不同类型的集合，以类似的方式工作，具有高度的互操作性。
对一个集合的扩展和适应必须是简单的。

## 接口：
  是代表集合的抽象数据类型。例如 Collection、List、Set、Map 等。之所以定义多个接口，是为了以不同的方式操作集合对象
### Collection 接口
  Collection 是最基本的集合接口，一个 Collection 代表一组 Object，即 Collection 的元素, Java不提供直接继承自Collection的类，只提供继承于的子接口(如List和set)。
  Collection 接口存储一组不唯一，无序的对象。
  
### List 接口
  List接口是一个有序的 Collection，使用此接口能够精确的控制每个元素插入的位置，能够通过索引(元素在List中位置，类似于数组的下标)来访问List中的元素，第一个元素的索引为 0，而且允许有相同的元素。
  List 接口存储一组不唯一，有序（插入顺序）的对象。
  
### Set
  Set 具有与 Collection 完全一样的接口，只是行为上不同，Set 不保存重复的元素。
  Set 接口存储一组唯一，无序的对象。
  
#### Set和List的区别：
  1. Set 接口实例存储的是无序的，不重复的数据。List 接口实例存储的是有序的，可以重复的元素。  
  2. Set检索效率低下，删除和插入效率高，插入和删除不会引起元素位置改变 <实现类有HashSet,TreeSet>。 
  3. List和数组类似，可以动态增长，根据实际存储的数据的长度自动增长List的长度。查找元素效率高，插入删除效率低，因为会引起其他元素位置改变 <实现类有ArrayList,LinkedList,Vector> 。
  
## 实现（类）：
  是集合接口的具体实现。从本质上讲，它们是可重复使用的数据结构，例如：ArrayList、LinkedList、HashSet、HashMap。
### 1 AbstractCollection 
  实现了大部分的集合接口。
###	2 AbstractList 
  继承于AbstractCollection 并且实现了大部分List接口。
###	3 AbstractSequentialList 
  继承于 AbstractList ，提供了对数据元素的链式访问而不是随机访问。
###	4 LinkedList
  该类实现了List接口，允许有null（空）元素。主要用于创建链表数据结构，该类没有同步方法，如果多个线程同时访问一个List，则必须自己实现访问同步，解决方法就是在创建List时候构造一个同步的List。例如：
  
  Listlist=Collections.synchronizedList(newLinkedList(...));
  LinkedList 查找效率低。


###	5 ArrayList
  该类也是实现了List的接口，实现了可变大小的数组，随机访问和遍历元素时，提供更好的性能。该类也是非同步的,在多线程的情况下不要使用。ArrayList 增长当前长度的50%，插入删除效率低。
### 6 AbstractSet 
  继承于AbstractCollection 并且实现了大部分Set接口。
### 7 HashSet
  该类实现了Set接口，不允许出现重复元素，不保证集合中元素的顺序，允许包含值为null的元素，但最多只能一个。
### 8  LinkedHashSet
  具有可预知迭代顺序的 Set 接口的哈希表和链接列表实现。
### 9  TreeSet
  该类实现了Set接口，可以实现排序等功能。
### 10 AbstractMap 
  实现了大部分的Map接口。

### 11	HashMap
  HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
  该类实现了Map接口，根据键的HashCode值存储数据，具有很快的访问速度，最多允许一条记录的键为null，不支持线程同步。
### 12	TreeMap 
  继承了AbstractMap，并且使用一颗树。
### 13	WeakHashMap 
  继承AbstractMap类，使用弱密钥的哈希表。
### 14	LinkedHashMap 
  继承于HashMap，使用元素的自然顺序对元素进行排序.
### 15	IdentityHashMap 
  继承AbstractMap类，比较文档时使用引用相等。
  
## 算法：
  是实现集合接口的对象里的方法执行的一些有用的计算，例如：搜索和排序。这些算法被称为多态，那是因为相同的方法可以在相似的接口上有着不同的实现。



SortedSet 
继承于Set保存有序的集合。
Map
Map 接口存储一组键值对象，提供key（键）到value（值）的映射。

Map.Entry 
描述在一个Map中的一个元素（键/值对）。是一个Map的内部类。

SortedMap
继承于 Map，使 Key 保持在升序排列。

Enumeration
这是一个传统的接口和定义的方法，通过它可以枚举（一次获得一个）对象集合中的元素。这个传统接口已被迭代器取代。


java 集合框架库主要框架粗讲
Java  集合框架库：
1. HashMap 和 HashSet 的默认大小是16。
HashMap 的容量大小需要保证为 2^n。
2. Hashtable 的默认大小是11。
3. ArrayList 和 Vector 的默认大小是10。
4. ArrayDeque 的默认大小是8。
5. PriorityQueue 的默认大小是11。
6.ConcurrentHashMap：16
Get（）方法没有加锁，是因为在get()的时候如果有别的线程在进行删除，他是重新构造一个新的链表，不会影响到get（）方法的读取，只是有可能get()方法读到的数据不是最新的数据，  当有别的线程进行增加的时候呢不会影响到get（）方法因为他采取的是尾插
Put（）调用put方法的时候不能传一个空值


1、Java中HashMap和Hashtable继承和实现的区别
　　Hashtable是基于陈旧的Dictionary类，完成了Map接口;HashMap是Java 1.2引进的Map接口的一个实现(HashMap继承于AbstractMap,AbstractMap完成了Map接口)。
2、 Java中HashMap和Hashtable线程安全的区别
HashTable的方法是同步的，HashMap是未同步，所以在多线程场合要手动同步HashMap。
3、 Java中HashMap和Hashtable对null的处理的区别
　　HashTable不允许null值(key和value都不可以，当要插入表空值的时候会抛出异常),HashMap允许null值(key和value都可以)。即 HashTable不允许null值其实在编译期不会有任何的不一样，会照样执行，只是在运行期的时候Hashtable中设置的话回出现空指针异常。 HashMap允许null值是指可以有一个或多个键所对应的值为null。当get()方法返回null值时，即可以表示 HashMap中没有该键，也可以表示该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键，而应该用containsKey()方法来判断。
4、Java中HashMap和Hashtable方法上的区别
HashTable有一个contains(Object value)，功能和containsValue(Object value)功能一样。
5、Java中HashMap和Hashtable使用区别
遍历方法：
HashTable使用Enumeration，interator，HashTable可以使用Keyset
的，HashMap使用Iterator entryset。
6、Java中HashMap和Hashtable默认大小的区别
HashTable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。
7、Java中HashMap和Hashtable哈希值的使用区别
　　HashTable直接使用对象的hashCode，代码是如下：
　　int hash = key.hashCode();
int index = (hash & 0x7FFFFFFF) % tab.length;
hash & 0x7FFFFFFF)去掉其符号位置。
　　而HashMap重新计算hash值，而且用与代替求模：
　　int hash = hash(key.Hashcode());
　　int i = indexFor(hash, table.length);
　　static int hash(Object x) {
　　int h = x.hashCode();
　　h += ~(h << 9);
　　h ^= (h >>> 14);
　　h += (h << 4);
h ^= (h >>> 10);
把后面几位移动到前面
　　return h;
　　}
　　static int indexFor(int h, int length) {   //散列值
　　return h & (length-1);
　　}
ArrayLis 和 LinkedList
1、ArrayList的大小是如何自动增加的？你能分享一下你的代码吗？
2、什么情况下你会使用ArrayList？什么时候你会选择LinkedList？
3、当传递ArrayList到某个方法中，或者某个方法返回ArrayList，什么时候要考虑安全隐患？如何修复安全违规这个问题呢？
4、如何复制某个ArrayList到另一个ArrayList中去？写出你的代码？
5、在索引中ArrayList的增加或者删除某个对象的运行过程？效率很低吗？解释一下为什么？



equals()和hashCode()区别？

equals()：反映的是对象或变量具体的值，即两个对象里面包含的值--可能是对象的引用，也可能是值类型的值。
hashCode()：计算出对象实例的哈希码，并返回哈希码，又称为散列函数。根类Object的hashCode()方法的计算依赖于对象实例的D（内存地址），故每个Object对象的hashCode都是唯一的；当然，当对象所对应的类重写了hashCode()方法时，结果就截然不同了。
之所以有hashCode方法，是因为在批量的对象比较中，hashCode要比equals来得快，很多集合都用到了hashCode，比如HashTable。
两个obj，如果equals()相等，hashCode()一定相等。
两个obj，如果hashCode()相等，equals()不一定相等（Hash散列值有冲突的情况，虽然概率很低）。
