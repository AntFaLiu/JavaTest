## 简介
    Queue继承自 Collection 结构如下：
        public interface Queue<E> extends Collection<E> {
            boolean add(E var1);
            boolean offer(E var1);
            E remove();
            E poll();
            E element();
            E peek();
        }
## 处理元素的方法
    在处理元素前用于保存元素的 collection。除了基本的 Collection 操作外，队列还提供其他的插入、提取和检查操作。
    每个方法都存在两种形式：一种抛出异常（操作失败时），另一种返回一个特殊值（null 或 false，具体取决于操作）。
    插入操作的后一种形式是用于专门为有容量限制的 Queue 实现设计的；在大多数实现中，插入操作不会失败。
### Queue 的抽象实现类
### AbstractQueue 是Queue 的抽象实现类，和Lst、Set 的抽象实现类一样，AbstractQueue 也继承自 AbstractCollection。
### AbstractQueue 实现的方法不多，主要就 add、remove、element 三个方法的操作失败抛出了异常。
## Deque
    (1)java.util.Deque<E>是一个线性collection,支持在两端插入和移除元素。（名称deque是"double ended queue" 双端队列）。
    (2)java.util.Deque<E>接口既支持有容量限制的双端队列，也支持没有固定大小限制的双端队列。
    (3)java.util.Deque<E>接口定义在双端队列访问元素的方法，提供插入、移除、检查元素的方法。
    每种方法都存在两种形式：一种形式在操作失败时抛出异常;另一种形式返回一个特殊值（null或false）

## ArrayDeque  队列  类提供了可调整大小的阵列，并实现了Deque接口。
    （1）数组双端队列没有容量限制，使他们增长为必要支持使用。 
    （2）它们不是线程安全的;如果没有外部同步。 
    （3）不支持多线程并发访问。 
    （4）null元素被禁止使用在数组deques。 
    （5）它们要比堆栈Stack和LinkedList快。
### 注意：在ArrayDeque中元素不能为null；因为不知道是我们添加进入的还是我们删除数据后设置的null值
### ArrayDeque 源码解析
### 属性解析
     /*** 数组作为存储结构*/
     private transient E[] elements;
     /*** 头节点下标*/
     private transient int head;
     /** * 尾节点下标*/
     private transient int tail;
     /*** 创建队列最小容量，容量必须是2的x次方*/
     private static final int MIN_INITIAL_CAPACITY = 8;
     
#### 构造函数
    如果没有指定显式传入elements的长度，则默认16。如果显式传入一个代表elements的长度的变量，
    那么会调用allocateElements做一些简单的处理，并不会简单的将你传入的参数用来构建elements，
    它会获取最接近numElements的2的指数值，比如：numElements等于30，那么elements的长度会为32，
    numElements为11，那么对应elements的长度为16。但是如果你传入一个小于8的参数，
    那么会默认使用我们上述介绍的静态属性值作为elements的长度。至于为什么这么做，
    因为这么做会大大提高我们在入队时候的效率，我们等会儿会看到。

#### /* 分配2的幂的容量 */
    private void allocateElements(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        //如果指定容量小于最小容量，则直接为最小容量
        if (numElements >= initialCapacity) {//保证会得到比指定容量大的2的幂
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
    
            if (initialCapacity < 0)
                initialCapacity >>>= 1;
        }
        elements = (E[]) new Object[initialCapacity];
    }
#### /* 创建一个容量翻倍后的数组，将旧数组的数据复制过去 */
     private void doubleCapacity() {
         assert head == tail;//尾巴碰到头了才去扩容
         int p = head;
         int n = elements.length;
         int r = n - p; 
         int newCapacity = n << 1;//X2 扩容为原来的2倍
         if (newCapacity < 0)//太大溢出了
             throw new IllegalStateException("Sorry, deque too big");
         Object[] a = new Object[newCapacity];
         System.arraycopy(elements, p, a, 0, r);//先把队头到数组末尾之间的数据复制过去
         System.arraycopy(elements, 0, a, r, p);//再把数组起始位置到队头之间的数据复制过去
         elements = (E[])a;
         head = 0;
         tail = n;
     }
#### copyElements
    /* 将ArrayDeque的内置数组数据复制到参数数组中 */
    private <T> T[] copyElements(T[] a) {
        if (head < tail) {//尾巴没有跑到头的左边时
            System.arraycopy(elements, head, a, 0, size());
        } else if (head > tail) {//尾巴跑到了头的左边
            int headPortionLen = elements.length - head;
            System.arraycopy(elements, head, a, 0, headPortionLen);
            System.arraycopy(elements, 0, a, headPortionLen, tail);
        }
        return a;
    }
#### /* 还有会抛出异常相同功能方法getFirst和getLast，一般只用不抛出异常的 */
     public E peekFirst() {
         return elements[head]; // elements[head] is null if deque empty
     }
     
     public E peekLast() {
         return elements[(tail - 1) & (elements.length - 1)];
     }
#### pollFirst和pollLast
     /* 删除第一个元素 */
     public E pollFirst() {
         int h = head;
         E result = elements[h];
         if (result == null)
             return null;
         elements[h] = null;//GC 
         //保证循环   
         head = (h + 1) & (elements.length - 1);
         return result;
     }
     /* 删除最后一个元素 */
     public E pollLast() {
         //保证循环
         int t = (tail - 1) & (elements.length - 1);
         E result = elements[t];
         if (result == null)
             return null;
         elements[t] = null;
         tail = t;
         return result;
     } 
     

####  public void addLast(E e) {
    if (e == null)
        throw new NullPointerException();
    elements[tail] = e;
    if ( (tail = (tail + 1) & (elements.length - 1)) == head)
    doubleCapacity();
}
####//往队尾添加元素;失败抛出异常;成功返回true
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }
    // 将指定元素插入此双端队列的末尾
    public boolean add(E e) {
        addLast(e);
        return true;

#### ArrayQueue 队列的相关操作
    1.push（E e）往队头添加元素完成栈的栈顶压入元素
     public E pop() {
            return removeFirst();
        }
    2.弹栈，删除队列的第一个元素
    public void push(E e) {
            addFirst(e);
        }    
## PriorityQueue 
    public class PriorityQueue<E> extends AbstractQueue<E> implements Serializable {
        private static final long serialVersionUID = -7720805057305804111L;
        private static final int DEFAULT_INITIAL_CAPACITY = 11;
        transient Object[] queue;
        private int size;
        private final Comparator<? super E> comparator;
        transient int modCount;
        private static final int MAX_ARRAY_SIZE = 2147483639;
    }
    直接继承自 AbstractQueue，并且除序列号接口外，没实现任何接口，大概算是最忠诚的 Queue 实现类吧。
    照惯例，我们先来看看 API 介绍。
    一个基于优先级堆的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 
    进行排序，具体取决于所使用的构造方法。优先级队列不允许使用 null 元素。
    依靠自然顺序的优先级队列还不允许插入不可比较的对象.此队列的头 是按指定排序方式确定的最小元素。
    如果多个元素都是最小值，则头是其中一个元素——选择方法是任意的。
    队列获取操作 poll、remove、peek 和 element 访问处于队列头的元素。
    优先级队列是无界的，但是有一个内部容量，控制着用于存储队列元素的数组大小。
    它通常至少等于队列的大小。随着不断向优先级队列添加元素，其容量会自动增加。无需指定容量增加策略的细节。
    
    
    ~~--	   ____第一个元素（头部）.....      	    _____最后一个元素（尾部）
    ~	         抛出异常	        特殊值	      抛出异常	                  特殊值
    插入	    addFirst(e)	    offerFirst(3)	  addLast(e)	            offerLast(3)
    移除	    removeFirst()	pollFirst()	      removeLast()	            pollLast()
    检查	    getFirst()	    peekFirst()	      getLast()	                peekLast()
 

   
    
