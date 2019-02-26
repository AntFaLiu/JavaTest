## 简介
    Queue继承自 collection 结构如下：
        public interface Queue<E> extends collection<E> {
            boolean add(E var1);
            boolean offer(E var1);
            E remove();
            E poll();
            E element();
            E peek();
        }
## 处理元素的方法
    在处理元素前用于保存元素的 collection。除了基本的 collection 操作外，队列还提供其他的插入、提取和检查操作。
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
### 继承了那些接口
    public class ArrayDeque<E> extends AbstractCollection<E>
                         implements Deque<E>, caiYuan.cloneable, Serializab{
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
    public ArrayDeque() {
        elements = new Object[16];
    }
    public ArrayDeque(int numElements) {
        allocateElements(numElements);
    }
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
     

####  public void addLast(E e) {            //该代码是往队列中追加元素， doubleCapacity()函数为扩容策略
    if (e == null)
        throw new NullPointerException();
    elements[tail] = e;
     //判断是否需要扩容是在tail+1的位置， elements[tail] = e是可以添加元素的
    if ( (tail = (tail + 1) & (elements.length - 1)) == head)
    doubleCapacity();
}
#### if ( (tail = (tail + 1) & (elements.length - 1)) == head)
    这条语句的判断条件还是比较难理解的，我们之前在构造elements元素的时候，
    说过它的长度一定是2的指数级，所以对于任意一个2的指数级的值减去1之后必然所有位全为1，
    例如：8-1之后为111，16-1之后1111。而对于tail来说，当tail+1小于等于elements.length - 1，
    两者与完之后的结果还是tail+1，但是如果tail+1大于elements.length - 1，两者与完之后就为0，回到初始位置。
    这种判断队列是否满的方式要远远比我们使用符号%直接取模高效，jdk优雅的设计从此可见一瞥。接着，如果队列满，
    那么会调用方法doubleCapacity扩充容量，

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
        public void addFirst(E e) {
                if (e == null)
                    throw new NullPointerException();
                elements[head = (head - 1) & (elements.length - 1)] = e;
                if (head == tail)
                    doubleCapacity();
            }
     
    2.弹栈，删除队列的第一个元素
    public void push(E e) {
            addFirst(e);
     }
    public E removeFirst() {
             E x = pollFirst();
             if (x == null)
                 throw new NoSuchElementException();
             return x;
    }
     
      public E pollFirst() {
             int h = head;
             @SuppressWarnings("unchecked")
             E result = (E) elements[h];
             // Element is null if deque empty
             if (result == null)
                 return null;
             elements[h] = null;     // Must null out slot
             head = (h + 1) & (elements.length - 1);
             return result;
    }
         
## PriorityQueue 
### 简介
    1、无边界的优先队列，间接实现自Queue
    会有一个内部的capacitty，这个capacity初始值为11，这个值会自动增长
    2、传入元素必须实现Comparable接口，或者通过带有Comparator的比较器的构造函数来定义，两者必须任选其一。如果既有Comparator传进去，元素又实现了Comparable接口，优先Comparator。
    3、线程不安全，多线程程序，应该使用PriorityBlockingQueue
    4、iterator方法不保证顺序，如果想拿出完全有序的列表，需要自己排序，如使用Arrays.sort进行排序
    5、入列、出列方法，offer() add() poll() remove()的时间复杂度为O(log(n))；
    检索方法，peek()、element()和size方法的时间复杂度为O(1)；
    remove(Object)和contains(Object)方法时间复杂度为线性，O(n)；
    6、PriorityQueue是基于堆排序实现的，具体先看排序--堆排序，这里很多地方不细讲。
    
    直接继承自 AbstractQueue，并且除序列号接口外，没实现任何接口，大概算是最忠诚的 Queue 实现类吧。
    照惯例，我们先来看看 API 介绍。
    一个基于优先级堆的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 
    进行排序，具体取决于所使用的构造方法。优先级队列不允许使用 null 元素。
    依靠自然顺序的优先级队列还不允许插入不可比较的对象.此队列的头 是按指定排序方式确定的最小元素。
    如果多个元素都是最小值，则头是其中一个元素——选择方法是任意的。
    队列获取操作 poll、remove、peek 和 element 访问处于队列头的元素。
    优先级队列是无界的，但是有一个内部容量，控制着用于存储队列元素的数组大小。
    它通常至少等于队列的大小。随着不断向优先级队列添加元素，其容量会自动增加。无需指定容量增加策略的细节。
### 构造函数
    public class PriorityQueue<E> extends AbstractQueue<E> implements Serializable {
        private static final long serialVersionUID = -7720805057305804111L;
        private static final int DEFAULT_INITIAL_CAPACITY = 11;
        transient Object[] queue;
        private int size;
        private final Comparator<? super E> comparator;
        transient int modCount;
        private static final int MAX_ARRAY_SIZE = 2147483639;
    }
### 变量
    DEFAULT_INITIAL_CAPACITY 初始容量，默认是11，构造函数不指定容量时，默认使用的初始化容量
    Object[] queue 数组，存放数据
    size 大小，这个大小并不是数组的容量，数组容量一定大等于size，size表示最后一个有效数字的index+1，或者说队列长度，size每次入列出列remove都会变化，所以获取大小时，直接获取O(1)
    comprator 比较器，如果有比较器，优先使用比较器，如果比较器 == null，要求元素必须实现IComparator接口，否则会在进行排序操作的时候报错。
    modCount 表示被修改（structurally modified 结构化变更，意思是）的次数，用于迭代器next和remove的时候判断是否数据仍然发生了结构化变更。迭代器在初始化时，拿一次当时的modCount，next和remove的时候，会判断旧的modCount和最新的modCount是否相同。如果不同会抛出ConcurrentModificationException
  
 
    
    
    ~~--	   ____第一个元素（头部）.....      	    _____最后一个元素（尾部）
    ~	         抛出异常	        特殊值	      抛出异常	                  特殊值
    插入	    addFirst(e)	    offerFirst(3)	  addLast(e)	            offerLast(3)
    移除	    removeFirst()	pollFirst()	      removeLast()	            pollLast()
    检查	    getFirst()	    peekFirst()	      getLast()	                peekLast()
 
 ### 构造函数
      public PriorityQueue(int initialCapacity,
                                  Comparator<? super E> comparator) {
                 // Note: This restriction of at least one is not actually needed,
                 // but continues for 1.5 compatibility
                 if (initialCapacity < 1)
                     throw new IllegalArgumentException();
                 this.queue = new Object[initialCapacity];
                 this.comparator = comparator;
       }
###    private void grow(int minCapacity) {
    int oldCapacity = queue.length;
    // Double size if small; else grow by 50%
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                     (oldCapacity + 2) :
                                     (oldCapacity >> 1));
    // overflow-conscious code
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    queue = Arrays.copyOf(queue, newCapacity);
    }
    上面的代码对队列扩容，源码中注释也很清晰，首先判断当前的数组大小是否足够小(<64)
    如果足够小则将大小扩充为2倍，否则将原大小加上50%。需要注意的是，这里最后做了
    一个大小是否溢出的判断。
### heapify()
    建堆方法
    1、会在初始化时候调用heapify()方法，构建堆。先看堆排序，这里堆方面不会那么细讲。
    2、从n/2-1处开始到0，不停的调整堆，成为小顶堆。调用siftDown构建小顶堆，siftDown字面意思是向下筛选，就是从父节点开始向左右节点找最小值。
    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }
   
### // 构建小顶堆
    // 下面是siftDown方法，有两个，一个使用比较器来实现比较，一个使用元素的CompareTo方法来实现，其实一样
    // 这个构建堆的方法比我写的好多了，哈哈
    private void siftDownUsingComparator(int k, E x) {
        // 有边界，因为是向下构建，只用到倒数第一个非叶子节点就可以了
        int half = size >>> 1;
        // 循环的原因是，假设某个节点出现了交换，父节点和子节点交换，为了保证交换下去的父节点和以下的节点也能构成小顶堆，需要递归
        // 最多递归到结束
        // 如果遇到不需要交换的情况，说明到此为止都能满足小顶堆的要求，break
        while (k < half) {
            // 取左子节点，左子节点一定存在，所以先取左子节点
            int child = (k << 1) + 1;
            // 取左子节点数值
            Object c = queue[child];
            // 取右子节点
            int right = child + 1;
            // 如果右子节点存在，取左右子节点中的较小值
            // comparator.compare((E) c, (E) queue[right]) > 0说明右子节点是较小值，让c等于右子节点的值，child等于右子节点的index
            if (right < size &&
                    comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            // 比较父节点和较小子节点，如果小等于，说明不用换，break
            if (comparator.compare(x, (E) c) <= 0)
                break;
            // 将k位置赋值成较小值
            queue[k] = c;
            // 因为出现了交换，为了保证交换下去的节点也能满足小顶堆，需要继续调整堆
            // 指定k为child，进入下一次循环
            k = child;
        }
        // 如果结束，说明k应该的位置，就是x
        queue[k] = x;
  
### public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        siftUp(i, e);
    return true;
}
###  public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);
    return result;
    1、如果size为0，return null
    2、size-1，并且拿到size-1处的数据。size-1就会让有效数据减少一个，拿到size-1处，也就是当前最后的数据。
    3、modCount++
    4、取0位置的元素，准备拿出来
    5、将最后的元素丢到顶上，然后调用siftDown方法，让它下降到合适的位置。
    6、同样这个元素的行进路径，只会是树中的任一分支，所以复杂度也是O(logn)
    7、极端的例子，假设队列中全部是相同的元素，最后的元素不就跑到第一位去了
    这个方法首先将数组第一个元素作为结果，(因为如果是小顶堆的话堆顶始终是最小元素)，并将队列的最后一个元素放到第一个位置，最后用
    siftDown做一些调整，保证队列的性质，这个操作被称为下滤(percolate down)。


###  siftUp()方法
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
    和siftDown一样，有shiftUpUsingComparator，或者shiftUpComparable，同样，
    两个方法类似，如果是使用传入比较器的构造函数，则使用Comparator，否则使用元素自身的compareTo方法。
    shiftUp方法两个参数，一个是当前入队元素的位置，也就是堆的最后一个元素，然后开始和父节点开始比较，
    如果当前比父节点小（那么一定比左节点小），和父节点交换，然后继续和父节点比较，直到满足大等于父节点break，
    或者到堆顶。这个操作只会经过树的某一个分支。
###  private void siftUpComparable(int k, E x) {
            Comparable<? super E> key = (Comparable<? super E>) x;
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                Object e = queue[parent];
                if (key.compareTo((E) e) >= 0)
                    break;
                queue[k] = e;
                k = parent;
            }
            queue[k] = key;
        }
### private void siftUpUsingComparator(int k, E x) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                Object e = queue[parent];
                if (comparator.compare(x, (E) e) >= 0)
                    break;
                queue[k] = e;
                k = parent;
            }
            queue[k] = x;
        }
    为了保证优先队列的性质1，在插入每个元素时都需要与该节点父亲进行比较，找到其正确位置，
    有些数据结构书中，这个操作被称为上滤(percolate up)。     

### private E removeAt(int i) {
    assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    if (s == i) // removed last element  如果删除的元素是最后一个元素的话直接置空
        queue[i] = null;
    else {
        E moved = (E) queue[s]  
        //若果删除的不是最后一个元素，现将最后一个元素置空然后调用siftDown()方法调整;  
        queue[s] = null;                                     
        siftDown(i, moved);   讲最后一个元素放在之前i的位置上进行调整
        if (queue[i] == moved) {  
        //这个情况说明moved是三个节点中最大的元素，这样的话就需要再进行一次调整，使得
            siftUp(i, moved);
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
   }