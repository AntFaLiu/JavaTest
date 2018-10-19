package Collection;

//1、ArrayList的大小是如何自动增加的？你能分享一下你的代码吗？
//ArrayList Add方法：
//public boolean add(E e){
//        ensureCapacity(size+1); //Increment modCount!!
//        elementData[size++] = e;
//        return true;
//        }
//
////ensureCapacity方法：处理ArrayList的大小
//public void ensureCapacity(int minCapacity) {
//        modCount++;
//        int oldCapacity = elementData.length;
//        if (minCapacity > oldCapacity) {
//        Object oldData[] = elementData;
//        int newCapacity = (oldCapacity * 3)/2 + 1;
//        if (newCapacity < minCapacity)
//        newCapacity = minCapacity;
//        // minCapacity is usually close to size, so this is a win:
//        elementData = Arrays.copyOf(elementData, newCapacity);
//        }
//        }


//2、什么情况下你会使用ArrayList？什么时候你会选择LinkedList？
//  这又是一个大多数面试者都会困惑的问题。多数情况下，当你遇到访问元素比插入或者是删除元素更加频繁的时候，你应该使用ArrayList。
//  另外一方面，当你在某个特别的索引中，插入或者是删除元素更加频繁，或者你压根就不需要访问元素的时候，你会选择LinkedList。这里的主要原因是，
//  在ArrayList中访问元素的最糟糕的时间复杂度是”1″，而在LinkedList中可能就是”n”了。在ArrayList中增加或者删除某个元素，通常会调用System.arraycopy方法，
//  这是一种极为消耗资源的操作，因此，在频繁的插入或者是删除元素的情况下，LinkedList的性能会更加好一点。

//3.当传递ArrayList到某个方法中，或者某个方法返回ArrayList，什么时候要考虑安全隐患？如何修复安全违规这个问题呢？
//        当array被当做参数传递到某个方法中，如果array在没有被复制的情况下直接被分配给了成员变量，那么就可能发生这种情况，即当原始的数组被调用的方法改变的时候，传递到这个方法中的数组也会改变。下面的这段代码展示的就是安全违规以及如何修复这个问题。
//
//        ArrayList被直接赋给成员变量——安全隐患：
//public void setMyArray(String[] myArray){
//        this.myArray = myArray;
//        }
//public void setMyArray(String[] newMyArray){
//        if(newMyArray == null){
//        this.myArray = myArray;
//        }else{
//        this.myArray = Arrays.copyOf(newMyArray,newMyArray);
//        }
//
//        }

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//4、如何复制某个ArrayList到另一个ArrayList中去？写出你的代码？
//        下面就是把某个ArrayList复制到另一个ArrayList中去的几种技术：
//
//        使用clone()方法，比如ArrayList newArray = oldArray.clone();
//
//        使用ArrayList构造方法，比如：ArrayList myObject = new ArrayList(myTempObject);
//
//        使用Collection的copy方法。
//
//        注意1和2是浅拷贝(shallow copy)。
//
//        5、在索引中ArrayList的增加或者删除某个对象的运行过程？效率很低吗？解释一下为什么？
//        在ArrayList中增加或者是删除元素，要调用System.arraycopy这种效率很低的操作，如果遇到了需要频繁插入或者是删除的时候，你可以选择其他的Java集合，比如LinkedList。看一下下面的代码：
//
//        在ArrayList的某个索引i处添加元素：
public class ArrayListTest {


    public static void main(String[] args) {
        List list = new ArrayList();
        list.get();
        list.remove();
        ((ArrayList) list).clone();
        Arrays.copyOf()
    }
}
