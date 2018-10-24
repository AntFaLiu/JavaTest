Vector是基于可变数组的List接口的同步实现
Vector是有序的
Vector允许null键和null值
Vector已经不建议使用了
## 增长系数的作用
    当数组需要扩容的时候，如果增长系数不为零的话 就在原有的数组大小上加上增长系数作为现在数组的大小，如果没有指定的话容量就二倍增长
## 实现了哪些接口
    Vector实现了List接口、底层使用数组保存所有元素，其操作基本上是对数组的操作
    Vector继承了AbstractList抽象类，它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能
    Vector实现了RandmoAccess接口，即提供了随机访问功能，RandmoAccess是java中用来被List实现，为List提供快速访问功能的，我们可以通过元素的序号快速获取元素对象，这就是快速随机访问
    Vector实现了Cloneable接口，即覆盖了函数clone()，能被克隆
    Vector实现了java.io.Serializable接口，意味着ArrayList支持序列化
## vector因为很多方法都跟ArrayList一样，只是多加了个synchronized来保证线程安全罢了.

3、Vector创建时的默认大小为10。

4、Vector每次扩容都以当前数组大小的2倍去扩容。当指定了capacityIncrement之后，每次扩容仅在原先基础上增加capacityIncrement个单位空间。

Vector是同步访问的。
Vector包含了许多传统的方法，这些方法不属于集合框架。

    属性：
    // 保存Vector中数据的数组  
    protected Object[] elementData;  
    // 实际数据的数量  
    protected int elementCount;  
    // 容量增长系数  
    protected int capacityIncrement;  
    // Vector的序列版本号  
    private static final long serialVersionUID = -2767605614048989439L;
## Vector构造函数。默认容量是10。  
    public Vector() {  
        this(10);       //也就是说默认情况下增长系数为0
    }  

## 指定Vector容量大小的构造函数  
    public Vector(int initialCapacity) {  
        this(initialCapacity, 0);  
    }  

## 指定Vector"容量大小"和"增长系数"的构造函数  
    public Vector(int initialCapacity, int capacityIncrement) {  
        super();  
        if (initialCapacity < 0)  
            throw new IllegalArgumentException("Illegal Capacity: "+  
                                               initialCapacity);  
        // 新建一个数组，数组容量是initialCapacity  
        this.elementData = new Object[initialCapacity];  
        // 设置容量增长系数  
        this.capacityIncrement = capacityIncrement;  
    }  

## 指定集合的Vector构造函数。  
    public Vector(Collection<? extends E> c) {  
        // 获取“集合(c)”的数组，并将其赋值给elementData  
        elementData = c.toArray();  
        // 设置数组长度  
        elementCount = elementData.length;  
        // c.toArray might (incorrectly) not return Object[] (see 6260652)  
        if (elementData.getClass() != Object[].class)  
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);  
    } 
    
## public synchronized E firstElement() {  获取第一个元素
        if (elementCount == 0) {
            throw new NoSuchElementException();
        }
        return elementData(0);
    }
## public synchronized E lastElement() {
    if (elementCount == 0) {
            throw new NoSuchElementException();
    }
        return elementData(elementCount - 1);
    }   
## elementData(int index)或get(int index)  获取index位置元素，两个方式最大区别于是否抛出异常
    E elementData(int index) {
        return (E) elementData[index];
    }
    //另一种方式
    public synchronized E get(int index) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
    
        return elementData(index);
    }
    
## public synchronized E remove(int index) {
    modCount++;
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);
    E oldValue = elementData(index);

    int numMoved = elementCount - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--elementCount] = null; // Let gc do its work

    return oldValue;

## public synchronized void removeElementAt(int index) {
        modCount++;
        if (index >= elementCount) { // 越界
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                                                     elementCount);
        }
        else if (index < 0) { // 越界
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1; // 需要移动元素个数
        if (j > 0) { // elementData数组，从index+1开始复制j个元素，到该数组的index开始位置
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--; // 元素个数-1 
        elementData[elementCount] = null; // 空，有利于垃圾回收
    }
## Vector和ArrayList的区别
    Vector	ArrayList
    同步、线程安全的	异步、线程不安全
    需要额外开销来维持同步锁，性能慢	性能快
    可以使用Iterator、foreach、Enumeration输出	只能使用Iterator、foreach输出
