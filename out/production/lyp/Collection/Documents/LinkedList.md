## 简介
    LinkedList是Java集合中常用的容器之一，内部是一个双向链表（不是循环的），实现了List和Deque接口，
    可以同时当作列表、队列和栈来使用，它提供了一系列对链表元素进行增删改查的方法，有很多功能差不多但名字不同的方法，
    有些针对空值抛异常而有些则返回特殊值，使用时最好将其上转型到相应的接口以限制它的功能。
### 继承哪些接口实现哪些类
    public class LinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable
### 原理及内部特点
    不是线程安全的，没有Synchronized关键字。
    允许向其中添加null。
    内部是由一个一个的内部类Node前后指针连接组成的链，LinkedList相当于一个Node的管理类（如下图所示），有两根指针first和last一直指向Node链的头部和尾部，其优势在于增加和删除元素较快，但不适合查找操作。
    其迭代器是快速失败的，在一个LinkedList的迭代器创建之后调用非迭代器中的方法对容器的内部结构进行修改都会抛出ConcurrentModificationException。
## 源码解析
### /* 头插法 */
    private void linkFirst(E e) {
        final Node<E> f = first;
        //创建一个新结点，其前指针为空，后指针指向first所指结点（可能为null）
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;//first指针始终指向Node链的头
        if (f == null)   //如果LinkedList刚创建，此时新创建的结点既然是头结点也是尾结点
            last = newNode;
        else
            f.prev = newNode;//如果头插之前有结点，将其前指针指向新结点
        size++;
        modCount++;//记录修改次数，迭代时检测快速失败
    }
    
