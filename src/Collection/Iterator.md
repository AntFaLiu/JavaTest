## 什么是迭代器
    1) boolean hasNext(); // 是否还有下一个元素（肯定有一个位置指针维护者当前迭代的位置）
    2) Object next(); // 取出下一个元素并返回
    3) void remove(); // 从容器中删除当前元素（即上一个next代表的那个元素），直接会改变容器中的数据！
### 优势
## 用迭代器遍历各种集合
### List
    在List中实现了两种迭代器，它可以从两个方向遍历List，也可以从List中插入和删除元素。
    
### iterator
    public Iterator<E> iterator() {
           return new Itr();
       }
       /**
        * An optimized version of AbstractList.Itr
        * 对父类的优化。- 这个抽象层次真好
        */
       private class Itr implements Iterator<E> {
           // 返回下一步元素的索引
           int cursor;       // index of next element to return
           //返回最后一次操作的元素索引，-1 表示没有操作过元素，用于删除操作使用
           int lastRet = -1; // index of last element returned; -1 if no such
           //快速失败检查，保留一个当前迭代器所持有的数据版本；
           int expectedModCount = modCount;
           //是否有下一个元素
           public boolean hasNext() {
               // 当前游标不等于size的话，就表示还有下一个元素
               return cursor != size;
           }
           @SuppressWarnings("unchecked")
           public E next() {
               checkForComodification();
               int i = cursor; //在开头获取下一步的值，且以0开始，则i表示当前操作的元素下标
               if (i >= size) /当前元素索引如果大于了size
                   throw new NoSuchElementException();
               Object[] elementData = ArrayList.this.elementData;
               // 判断 当前下标是否大于了数组的长度（什么情况下会出现此问题呢？）
               if (i >= elementData.length)
                   throw new ConcurrentModificationException();
               cursor = i + 1; //记录下一步要操作的元素
               return (E) elementData[lastRet = i];
           }
           public void remove() {
               // 如果还没有操作过元素，比如，没有调用next方法，就调用该方法就表示状态不正确
               if (lastRet < 0)
                   throw new IllegalStateException();
               checkForComodification(); //每次操作都需要检查是否快速失败
               try {
                   //调用原来的移除方法进行删除元素，当前也会使modCount次数增加
                   ArrayList.this.remove(lastRet);
                   cursor = lastRet; //因为删除了元素，删除下标后面的元素都会往前移动
                   lastRet = -1; //变成-1 状态，如果连续调用该方法则抛出异常（这里设计得真的好巧妙，保证了删除操作）
                   //更改当前迭代器所持有的数据版本，否则就会导致快速失败异常了
                   expectedModCount = modCount;
               } catch (IndexOutOfBoundsException ex) {
                   //对于数据越界操作，都定义为 当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常
                   throw new ConcurrentModificationException();
               }
           }
           //检查当前迭代器的版本是否与 容器列表中的数据版本是否一致，如果不一致，那么当前迭代数据就会发生下标越界等异常，所以需要抛出异常（比如在多线程中，或则在迭代中使用list.remove() 或则add等方法 都会导致抛出异常）
           final void checkForComodification() {
               if (modCount != expectedModCount)
                   throw new ConcurrentModificationException();
           }
       }
    
## contains(Object o) - 如果包含指定的元素返回true
    总结：
      1.查找是否包含同样是从头到尾的循环遍历匹配
      2.是否匹配 使用equals方法比对，所以自己可以覆盖equals方法来查找引用型自定义对象
    public boolean contains(Object o) {
            return indexOf(o) >= 0;
    }
        // 在数组中循环遍历查找指定的元素，如果存在则返回该元素下标，是返回首次出现的元素哦
    public int indexOf(Object o) {
            if (o == null) {
                for (int i = 0; i < size; i++)
                    if (elementData[i]==null)
                        return i;
            } else {
                for (int i = 0; i < size; i++)
                    //使用的是equals方法
                    if (o.equals(elementData[i]))
                        return i;
        }
            return -1;
    }
        
    优点：
      1.随机访问比较快，因为直接通过下标获取元素
      2.覆盖更新元素也比较快，也是直接通过下标覆盖
    缺点
      1.在remove多的场景下性能稍微低下（针对其他的集合来说）
      2.在按value remove的情况下会循环遍历整个数组，性能稍低
      3.随机插入操作性能较低，因为需要把插入下标后面所有的元素都移动位置
      4.当前容量不够的时候会触发扩容操作，每次扩容都需要把源数据拷贝到新的扩容数组中去，不合理估算初始容量的话，性能较低