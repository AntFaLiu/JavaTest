LinkedList

实现两个List ，各插入100个数(1-100和101-200)，第一个list使用迭代器遍历，第二个使用ForEach遍历。
删除第37个元素，分别使用Iterator提供的remove方法和本身提供的集合remove方法  
打印第50个元素使用get（）  contains（“aaa”）                                                                                                                              
foreach遍历   两种迭代器遍历  使用ListIterator替换第32个元素

public class LinkedList<E>  object  
    implements List<E>    可重复、null、有序  pre next  
	, Deque<E>,
	caiYuan.cloneable, clone   jvm底层
	java.io.Serializable  可序列化   readobject   writeobject
	
	单链表的插入删除   只写方法   不用写类（）  在增删 改查的时候是不要注意 下标是否合法 

双链表   
fast last  
x.pre   x.next    
  
  双链表的插入删除  -----》手写一个不带头节点的双链表（前驱后继value）-------》两个指针分别记录头和尾  实现头插尾插  
  在一个节点之后插入在一个节点之前插入  其实这些操作都是LInkedList的内部操作
  
  基本操作    
  
  数据结构： 
  
  双向链表    

区别 ArrayList  和linkedList区别   
1.    方法
2.    数据结构 数组  双向链表  
       get（）  插入删除的时候  Sytem.arraycopy  
	   index   node（）   
使用环境：

for(int i ;i < 10000000){
    add(i);
}
3.线程安全性问题    vetctor  线程安全  grow（） 2  初始容量  11  10
        collection c = Collections.synchronizedCollection(new ArrayList());
        List list = Collections.synchronizedList(new ArrayList());
    }
}


深拷贝     浅拷贝
class BanJi{
	Teacher t;
	
	Student s;
	
	int num;
	
	String name;
	
	int[] age; //年龄
}
BanJi banji = new BanJi();

1. 一层一层的赋值

2. clone 

3. 序列化  aa=writeobject   = readObject（a）

String name  常量   final class   
1.不可变  2.常量池  3.继承   。。。 集合
2.clone String  === int    String 新建一个String对象  

hashcode  ===   equals 
提问
深拷贝

预习作业：
（1）该集合继承了哪些类实现了哪些接口，根据这些接口列出你觉得ArrayList具有哪些特点？

（2）学会使用该集合的增删改查。

（3）该集合各个属性的含义，根据这些属性的含义你认为他底层是什么样数据结构。

（4）需不需要扩容，如何扩容，几倍扩容？（看情况）

（5）根据该集合的特点你觉得你会在什么情况下使用它。（自由发挥）

（6） 哈希、哈希冲突、处理哈希冲突的方法。

public class HashMap<K,V>
    implements Map<K,V>, caiYuan.cloneable, Serializable
   
Map：
caiYuan.cloneable：

Serializable：

区别：

   void reverse(List list)：反转

   void shuffle(List list),随机排序

   void sort(List list),按自然排序的升序排序

   void sort(List list, Comparator c);定制排序，由Comparator控制排序逻辑

   void swap(List list, int i , int j),交换两个索引位置的元素

   void rotate(List list, int distance),旋转stance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。

线程安全的hashmap  Connetctions


 








  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  