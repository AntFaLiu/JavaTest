package SerializaTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArrayListSerializableDemo {

    public static void main(String[] args) {
        arrayListSerializableDemo();
    }

    private static void arrayListSerializableDemo() {

        List<String> stringList = new ArrayList<String>();
        //添加数据
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hello");
        stringList.add("图论");

        System.out.println("stringList: " + stringList);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
            objectOutputStream.writeObject(stringList);  //序列化
            objectOutputStream.close();
            File file = new File("stringlist");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            List<String> newStringList = (List<String>) objectInputStream.readObject(); //反序列化
            objectInputStream.close();

            if (file.exists()) {
                file.delete();
            }

            System.out.println("newStringList: "+newStringList);

        } catch (Exception e) {

            System.err.println(e);

        }
    }
}
/**
 * writeObject和readObject方法
 * 在ArrayList中定义了来个方法： writeObject和readObject。
 * 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，
 * 虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化。
 * 如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法
 * 以及 ObjectInputStream 的 defaultReadObject 方法。
 * 用户自定义的writeObject和readObject方法可以允许用户控制序列化的过程，
 * 比如可以在序列化的过程中动态改变序列化的数值。
 */

/**
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
 elementData = EMPTY_ELEMENTDATA;
 // Read in size, and any hidden stuff
 s.defaultReadObject();
 // Read in capacity
 s.readInt(); // ignored
 if (size > 0) {
 // be like clone(), allocate array based upon size not capacity
 ensureCapacityInternal(size);
 Object[] a = elementData;
 // Read in all elements in the proper order.
 for (int i = 0; i < size; i++) {
 a[i] = s.readObject();
 }
 }
 }
 **/
/**
 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
 // Write out element count, and any hidden stuff
 int expectedModCount = modCount;
 s.defaultWriteObject();
 // Write out size as capacity for behavioural compatibility with clone()
 s.writeInt(size);
 // Write out all elements in the proper order.
 for (int i = 0; i < size; i++) {
 s.writeObject(elementData[i]);
 }
 if (modCount != expectedModCount) {
 throw new ConcurrentModificationException();
 }
 }
 **/

/**
 * why transient
 *
 * ArrayList实际上是动态数组，每次在放满以后自动增长设定的长度值，如果数组自动增长长度设为100，
 * 而实际只放了一个元素，那就会序列化99个null元素。为了保证在序列化的时候不会将这么多null同时进行序列化，
 * ArrayList把元素数组设置为transient。
 *
 * why writeObject and readObject
 * 前面说过，为了防止一个包含大量空对象的数组被序列化，为了优化存储，所以，ArrayList使用transient来声明elementData。
 * 但是，作为一个集合，在序列化过程中还必须保证其中的元素可以被持久化下来，所以，通过重写writeObject 和
 * readObject方法的方式把其中的元素保留下来。
 * writeObject方法把elementData数组中的元素遍历的保存到输出流（ObjectOutputStream）中。
 * readObject方法从输入流（ObjectInputStream）中读出对象并保存赋值到elementData数组中。
 *
 * 如何自定义的序列化和反序列化策略
 * 答：可以通过在被序列化的类中增加writeObject 和 readObject方法。那么问题又来了：
 * 虽然ArrayList中写了writeObject 和 readObject 方法，但是这两个方法并没有显示的被调用啊。
 * 那么如果一个类中包含writeObject 和 readObject 方法，那么这两个方法是怎么被调用的呢?
 */
/**
 * 如果一个类中包含writeObject 和 readObject 方法，那么这两个方法是怎么被调用的?
 * 答：在使用ObjectOutputStream的writeObject方法和ObjectInputStream的readObject方法时，会通过反射的方式调用。
 */
/**
 * Serializable明明就是一个空的接口，它是怎么保证只有实现了该接口的方法才能进行序列化与反序列化的呢？
 * 其实这个问题也很好回答，我们再回到刚刚ObjectOutputStream的writeObject的调用栈：
 *
 * writeObject ---> writeObject0--->writeOrdinaryObject--->writeSerialData--->invokeWriteObject
 * 在进行序列化操作时，会判断要被序列化的类是否是Enum、Array和Serializable类型，
 * 如果不是则直接抛出NotSerializableException。
 */

/**
 * 1、如果一个类想被序列化，需要实现Serializable接口。否则将抛出NotSerializableException异常，
 * 这是因为，在序列化操作过程中会对类型进行检查，要求被序列化的类必须属于Enum、Array和Serializable类型其中的任何一种。
 * 2、在变量声明前加上该关键字，可以阻止该变量被序列化到文件中。
 * 3、在类中增加writeObject 和 readObject 方法可以实现自定义序列化策略
 */