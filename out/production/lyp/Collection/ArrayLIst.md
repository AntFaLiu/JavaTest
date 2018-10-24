## 概述
    首先先来个总体的认识，ArrayList底层是用数组实现的。在插入值时如果超过了当前数组的大小，则会进行扩容操作，每次增加的大小为原来大小的“一半”（偶数一半，奇数减一的一半），并且按照新的大小新建一个相同类型的数组，然后将原数组中的值copy进新的数组，并修改引用。 
    默认创建ArrayList情况下： 
    （1）、ArrayList是一个长度可变的数组集合，创建ArrayList实例时没有指定长度，当添加数据，而且数据个数小于10时，那么默认将会创建一个容量为10的数组存储数据；当数组不够长时，将会默认增长原来容量的一半。如果要自定义ArrayList的容量，可以通过调用ensureCapacity（int size）方法指定容量，也可以在创建ArrayList时指定容量，ArrayList最大的容量不能超出Integer.MAX_VALUE（2147483647）否则会抛出OutOfMemoryError异常。 
    （2）、不同的方法访问ArrayList的时间复杂度不一样。size, isEmpty, get, set,iterator,listIterator的时间复杂度是固定的，add、remove方法是不固定的，因为有可能需要移动元素。 
    （3）、ArrayList不是线程安全的，所以在多线程的环境下使用ArrayList需要注意ArrayList类型变量的线程同步问题。当然，有一种方式可以创建一个线程安全的ArrayList： 
    List list = Collections.synchronizedList(new ArrayList(…)); 
    （4）、ArrayList的迭代器 iterator 和 listIterator 方法返回的迭代器是快速失败 的。所谓快速失败，意思就是如果在迭代器已经创建了的情况下，任何时刻对ArrayList结构的修改，迭代器将会抛出一个ConcurrentModificationException异常。如下面代码中迭代器已经生成，但是还往ArrayList添加数据，那么此后的迭代过程是会抛出ConcurrentModificationException异常的： 
    ArrayList底层是基于数组来保存数据的，这个数组就保存着ArrayList的真实数据。内部定义了这个数组： 
    参考：https://blog.csdn.net/lzjlzjlzjlzjlzjlzj/article/details/52291703 
## 继承了哪些接口
    ArrayList继承了AbstractList，实现了List。它是一个数组队列，相当于动态数组。提供了相关的添加、删除、修改和遍历等功能。
    ArrayList实现了RandomAccess接口，即提供了随机访问功能。RandomAccess是java中用来被List实现，为List提供快速访问功能的。
    在ArrayList中，我们即可以通过元素的序号来快速获取元素对象，这就是快速随机访问。
    下文会比较List的“快速随机访问”和使用“Iterator迭代器访问”的效率。
    ArrayList实现了Cloneable接口，即覆盖了函数clone()，能被克隆。
    ArrayList实现了java.io.Serializable接口，这意味着ArrayList支持序列化，能通过序列化去传输。
    和Vector不同，ArrayList中的操作是非线程安全的。所以建议在单线程中使用ArrayList，在多线程中选择Vector或者CopyOnWriteArrayList。
## 常用方法的时间复杂度
    ArrayList和Vector的add、get、size方法的复杂度都为O(1)，remove方法的复杂度为O(n)。
    ArrayList是否允许空	    允许
    ArrayList是否允许重复数据	允许
    ArrayList是否有序	有序
    ArrayList是否线程安全	    非线程安全
## modCount用法
    根据上面的解释和我们追溯源码可以总结出：在这些线程不安全的集合中，在某些方法中，初始化迭代器时会给这个modCount赋值，
    如果在遍历的过程中，一旦发现这个对象的modCount和迭代器存储的modCount不一样，就会报错。    
    modCount是这个list被结构性修改的次数。    
    结构性修改是指：改变list的size大小，或者，以其他方式改变他导致正在进行迭代时出现错误的结果。    
    这个字段用于迭代器和列表迭代器的实现类中，由迭代器和列表迭代器方法返回。    
    如果这个值被意外改变，这个迭代器将会抛出 ConcurrentModificationException的异常来响应：next,remove,previous,set,add 这些操作。  
    在迭代过程中，他提供了fail-fast行为而不是不确定行为来处理并发修改。   
    子类使用这个字段是可选的，如果子类希望提供fail-fast迭代器，它仅仅需要在add(int, E),remove(int)方法（或者它重写的其他任何会结构性修改这个列表的方法）中添加这个字段。    
    调用一次add(int,E)或者remove(int)方法时必须且仅仅给这个字段加1，否则迭代器会抛出伪装的ConcurrentModificationExceptions错误。
    如果一个实现类不希望提供fail-fast迭代器，则可以忽略这个字段
## expectedModCount源码解释  
    迭代器认为支持列表应该有的modCount的值，如果违背了这个期望，迭代器会检测到这个并发修改。  
## fail-fast机制
    在线程不安全的集合中，如果使用迭代器的过程中，发现集合被修改，会抛出ConcurrentModificationExceptions错误，
    这就是fail-fast机制。对集合进行结构性修改时，modCount都会增加，在初始化迭代器时，
    modCount的值会赋给expectedModCount，在迭代的过程中，只要modCount改变了，
    int expectedModCount = modCount等式就不成立了，迭代器检测到这一点，就会抛出错误：currentModificationExceptions。
   
## 属性
    初始容量
    private static final int DEFAULT_CAPACITY = 10;
    final的空数组
    private static final Object[] EMPTY_ELEMENTDATA = {};
    存放元素的数组
    private transient Object[] elementData;
    数组大小
    private INTsize;
    计数器
    private static final long serialVersionUID = 8683452581122892189L;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData; // non-private to simplify nested class access  
    private int size;
    //MAX_ARRAY_SIZE：Integer的最大值，但是源码中实际的长度会比Integer的最大值小8
    疑问：为什么不直接赋值为Integer.MAX_VALUE。
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    
    对照这些常量就很好理解上面ArrayList容量实现自增长的原理：这里总结下： 
    （1）、如果当前ArrayList的数据为空，并且添加的数据个数小于10，那么java将会分配一个长度为10的数组来存放数据； 
    （2）、如果当前ArrayList的数据不为空，并且数组的大小可以容纳新增的数据，那么将不进行自增长操作； 
    （3）、如果当前ArrayList的数据不为空，并且数组的大小不可以容纳新增的数据，那么将进行自增长操作，并且是这样增长： 
    如果(size+size/2)>size+新增数据的个数，那么将ArrayList的容量增长到(size+size/2)大小； 
    如果size+size/2)小于size+新增数据的个数，那么将ArrayList的容量增长到size+新增数据的个数大小; 
    如果size+新增数据的个数>MAX_ARRAY_SIZE，那么将ArrayList的容量增长到Integer.MAX_VALUE大小，否则将ArrayList的容量增长到MAX_ARRAY_SIZE； 
    如果size+新增数据的个数>Integer.MAX_VALUE，则抛出OutOfMemoryError异常。
    
    这里需要注意一下，当ArrayList容量扩充完成之后会调用这样一句代码： 
    elementData = Arrays.copyOf(elementData, newCapacity); 这句代码的意思就是按照新的容量大小来创建一个新的数据，
    并且把原来数据的数据拷贝过来。不懂 Arrays.copyOf()看看便明白。

## 构造方法
    public ArrayList(int initialCapacity) {
            super();
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal Capacity: "+
                                                   initialCapacity);
            this.elementData = new Object[initialCapacity];
        }
     
    //没有参数，则赋值成为空数组，所有对象统一是同一个    
        public ArrayList() {
            super();
            this.elementData = EMPTY_ELEMENTDATA;
        }
     
     //参数为集合，依次加入
        public ArrayList(Collection<? extends E> c) {
            elementData = c.toArray();
            size = elementData.length;
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        }
### add（）
    新增一个数据的源码非常简单，如果不看ensureCapacityInternal(size + 1);这一句代码，实际上就是往数组的size索引这个位置放入一个数据。size是ArrayList的大小。注意，这里的size不是数组的大小，而是数组里真正存在数据的大小，也即是ArrayList的大小。比如ArrayList的size是10，但是数据的长度有可能是100，只有前10个位置存放了真正的数据。
    public boolean add(E e) {
       ensureCapacityInternal(size + 1);  // Increments modCount!!
       elementData[size++] = e;
       return true;
    }
### public void add(int index, E element) { 在指定的index位置添加一个数据，index之后的数据将依次往后移
          rangeCheckForAdd(index);
  
          ensureCapacityInternal(size + 1);  // Increments modCount!!
          System.arraycopy(elementData, index, elementData, index + 1,
                           size - index);
          elementData[index] = element;
          size++;
      }
      这里也是直接在数据的index位置放入一个数据，但是在这之前，判断了传入的index是否有越界；同时也判断了ArrayList是否需要扩充容量，并且通过数据拷贝的方式把index后的数据往后移。其实数据的移动都是通过调用System.arraycopy(）进行移动的。
### public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }
### public E remove(int index) { 方法移除元素之后，index后面的数据是往前移动，同样也是通过System.arraycopy(
          rangeCheck(index);
  
          modCount++;
          E oldValue = elementData(index);
  
          int numMoved = size - index - 1;
          if (numMoved > 0)
              System.arraycopy(elementData, index+1, elementData, index,
                               numMoved);
          elementData[--size] = null; // clear to let GC do its work
  
          return oldValue;
      }
### public boolean remove(Object o) {
    /因为list支持 null 元素，所以对null进行特殊处理
    if (o == null) {
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        //使用元素下标进行遍历判断
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
        return false;
    }
###  private void fastRemove(int index) {   //注意这是一个私有的方法 私有的删除元素方法，不进行边界检查和是否存在的检查，直接按照指定的索引进行删除
           modCount++;
           int numMoved = size - index - 1;
           if (numMoved > 0)
               System.arraycopy(elementData, index+1, elementData, index,
                                numMoved);
           elementData[--size] = null; // clear to let GC do its work
           
       }
### protected void removeRange(int fromIndex, int toIndex)  删除fromIndex到toIndex之间的全部元素
    {
       modCount++;
       //numMoved为删除索引后面的元素个数
       int numMoved = size - toIndex;  
       //将删除索引后面的元素复制到以fromIndex为起始位置的存储空间中去
       System.arraycopy(elementData, toIndex, elementData, fromIndex,numMoved);
       int newSize = size - (toIndex-fromIndex);
       //将ArrayList后面(toIndex-fromIndex)个元素置为null
       for (int i = newSize; i < size; i++)
       {
           elementData[i] = null;
       }
       size = newSize;
    }
### public boolean removeAll(Collection<?> c) { // 移除ArrayList中Collection所包含的所有元素
       return batchRemove(c, false);
    }
### public boolean retainAll(Collection<?> c) { // 保留所有ArrayList和Collection共有的元素
       return batchRemove(c, true);
    }
    /* 移除和保留是相反的操作，complement表示是否进行相反操作 */
### private boolean batchRemove(Collection<?> c, boolean complement){
       final Object[] elementData = this.elementData;
       int r = 0, w = 0;
       boolean modified = false;
       try {
           for (; r < size; r++)//包含这个元素则选择覆盖或跳过
               if (c.contains(elementData[r]) == complement)
                   elementData[w++] = elementData[r];
       } finally {
          // Preserve behavioral compatibility with AbstractCollection,
       // even if c.contains() throws.
       if (r != size) {    进入finally说明出现异常   将后面的元素全部拷贝进数组
           System.arraycopy(elementData, r,
                            elementData, w,
                            size - r);
           w += size - r;
       }
       if (w != size) {    //说明数组容量减小了，说明有一些不是true的就需要把不是的置为null
           for (int i = w; i < size; i++)
               elementData[i] = null;
           modCount += size - w;
           size = w;
           modified = true;
       }
       return modified;
    }
### private void writeObject(java.io.ObjectOutputStream s) // java.io.Serializable的写入函数 // 将ArrayList的“容量，所有的元素值”都写入到输出流中
                                                            
        throws java.io.IOException{
        // 写出元素计数和任何隐藏的东西
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // 写出大小作为与clone（）的行为兼容性的容量
        s.writeInt(size);

        // 按正确的顺序写出所有元素
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
###  private void readObject(java.io.ObjectInputStream s) java.io.Serializable的读取函数：根据写入方式读出 // 先将ArrayList的“容量”读出，然后将“所有的元素值”读出
        throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // 读入大小和任何隐藏的东西
        s.defaultReadObject();

        // 读入容量
        s.readInt(); // ignored

        if (size > 0) {
            //就像clone（）一样，根据大小而不是容量来分配数组
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // 按正确的顺序读入所有元素
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }

### ensureCapacityInternal（）
    private void ensureCapacityInternal(int minCapacity) {
            if (elementData == EMPTY_ELEMENTDATA) {
                minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
 
        ensureExplicitCapacity(minCapacity);
    }
 
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
 
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
### public void clear() {
           modCount++;
   
           // clear to let GC do its work
           for (int i = 0; i < size; i++)
               elementData[i] = null;
   
           size = 0;
       }
       
### public int indexOf(Object o) {
           if (o == null) {
               for (int i = 0; i < size; i++)
                   if (elementData[i]==null)
                       return i;
           } else {
               for (int i = 0; i < size; i++)
                   if (o.equals(elementData[i]))
                       return i;
           }
           return -1;
       }

### public int lastIndexOf(Object o) {
          if (o == null) {
              for (int i = size-1; i >= 0; i--)
                  if (elementData[i]==null)
                      return i;
          } else {
              for (int i = size-1; i >= 0; i--)
                  if (o.equals(elementData[i]))
                      return i;
          }
          return -1;
      } 
    
### //private void grow(int minCapacity) { 
    这才是动态扩展的精髓，看到这个方法，ArrayList瞬间被打回原形  
       int oldCapacity = elementData.length;
       //首先得到数组的旧容量，然后进行oldCapacity + (oldCapacity >> 1)，将oldCapacity 右移一位，其效果相当于oldCapacity /2，整句的结果就是设置新数组的容量扩展为原来数组的1.5倍
       int newCapacity = oldCapacity + (oldCapacity >> 1);
       //再判断一下新数组的容量够不够，够了就直接使用这个长度创建新数组， 
       //不够就将数组长度设置为需要的长度
       if (newCapacity - minCapacity < 0)
           newCapacity = minCapacity;
       //判断有没超过最大限制，如果超出限制则调用hugeCapacity
       if (newCapacity - MAX_ARRAY_SIZE > 0)
           newCapacity = hugeCapacity(minCapacity);
       //将原来数组的值copy新数组中去， ArrayList的引用指向新数组
       //这儿会新创建数组，如果数据量很大，重复的创建的数组，那么还是会影响效率，
       //因此鼓励在合适的时候通过构造方法指定默认的capaticy大小
       elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
### private static int hugeCapacity(int minCapacity) {
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
        }
        
    接下来定义一个旧容量oldCapacity，值为elementData数组长度
    然后定义一个新容量newCapacity，值为旧容量oldCapacity加上（旧容量oldCapacity右移1位，也就是除以2，不四舍五入）
    接着比较新容量newCapacity和传入的最小容量minCapacity，谁大就赋值给新容量newCapacity
    如果新容量newCapacity比定义的数组最大容量MAX_ARRAY_SIZE还大的话，就调用hugeCapacity方法，看是否抛出异常还是赋最大值
    调用Arrays.copyOf进行数组扩容  
    
    对ArrayList进行自增长的入口是ensureCapacityInternal(int minCapacity)方法，这里说明一下实现自增长过程中有几个会使用到的常量： 
    （1）、elementData：这个变量在前面已经说过，是存放ArrayList真实数据的数组； 
    （2）、EMPTY_ELEMENTDATA：这是一个容量为0的空数组，这个常量是不会存储真实数据的，即它永远都是空的。 源码是这样定义的：
   
### public int indexOf(Object o) {
    if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    
### public int lastIndexOf(Object o) {
    if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
### public E set(int index, E element) {  用指定的元素替代此列表中指定位置上的元素
    //在remove中也使用了该函数，检查 index>=siez
        rangeCheck(index);
    //记录替换之前的元素
        E oldValue = elementData(index);
    //直接更新
        elementData[index] = element;
        return oldValue;
    }
### public E get(int index) {
       //检查下标是否越界size
       rangeCheck(index);
       //直接通过下标拿到了元素
       return elementData(index);
    }
### E elementData(int index) {  获得指定下标的 元素
      return (E) elementData[index];
    }
        
### public Object clone() {
    try {
        ArrayList<?> v = (ArrayList<?>) super.clone();
        v.elementData = Arrays.copyOf(elementData, size);
        v.modCount = 0;
        return v;
    } catch (CloneNotSupportedException e) {
        // this shouldn't happen, since we are Cloneable
        throw new InternalError(e);
    }
    ArrayList底层实现原理是通过数组实现，因此其优点是随机访问效率比较高，但是随机插入和删除元素比较慢，因为要对其它元素进行移动。
    
### private void rangeCheckForAdd(int index){ //判断是否出现下标是否越界 
    //如果下标超过了集合的尺寸 或者 小于0就是越界  
    if (index > size || index < 0)
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
### public int size() {
        return size;
    }
 
## public boolean isEmpty() {
        return size == 0;
    }
## final void checkForComodification() {
      if (modCount != expectedModCount)
           throw new ConcurrentModificationException();
    }
    /**
    * 在对一个集合对象进行跌代操作的同时，并不限制对集合对象的元素进行操作
    * 这些操作包括一些可能引起跌代错误的add()或remove()等危险操作。
    * 在AbstractList中，使用了一个简单的机制来规避这些风险。 
    * 这就是modCount和expectedModCount的作用所在
    */
    
    

### toArray()异常问题
     调用toArray()函数会抛出"java.lang.ClassCastException"异常，但是调用toArray(T[] contents)能正常返回T[]。
     toArray()会抛出异常是因为toArray()返回的是Object[]数组，将Object[]转换为其它类型（比如将Object[]转换为Integer[]）
     则会抛出"java.lang.ClassCastException"异常，因为java不支持向下转型。解决该问题的办法是调用<T> T[] toArray(T[] contents)，
     而不是Object[] toArray()。
  
### ArrayList和Vector的区别
    1、Vector是线程安全的，ArrayList是线程非安全的
    2、Vector可以指定增长因子，如果该增长因子指定了，那么扩容的时候会每次新的数组大小会在原数组的大小基础上加上增长因子；如果不指定增长因子，那么就给原数组大小*2
    
    
    jdk1.8中
    it.forEachRemaining(ele -> System.out.println(ele)); // 打印每个元素
    
    
    