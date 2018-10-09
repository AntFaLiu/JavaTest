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
