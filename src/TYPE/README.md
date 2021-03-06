## Java  泛型
目的：让编译器在编译的时候就发现错误，类型更安全，消除强制类型转换
Java  中的泛型是编译器层面的来实现的
核心实现方法--------类型擦除原则：
转换规则如下:
1.List<String>,List<Integer>,List<T>泛型擦除后的类型为List.
2.List<String>[]擦除之后的类型是List[]
3.List<? extends E> 下界 List<? super E>上界 擦除之后对应类型是List<E>
4.List<T extends Serializable & caiYuan.cloneable>擦除之后为List<Serializable>

类型擦除
正确理解泛型概念的首要前提是理解类型擦除（type erasure）。 Java中的泛型基本上都是在编译器这个层次来实现的。在生成的Java字节代码中是不包含泛型中的类型信息的。使用泛型的时候加上的类型参数，会被编译器在编译的时候去掉。这个过程就称为类型擦除。如在代码中定义的List<Object>和List<String>等类型，在编译之后都会变成List。JVM看到的只是List，而由泛型附加的类型信息对JVM来说是不可见的。Java编译器会在编译时尽可能的发现可能出错的地方，但是仍然无法避免在运行时刻出现类型转换异常的情况。类型擦除也是Java的泛型实现方式与C++模板机制实现方式之间的重要区别。
很多泛型的奇怪特性都与这个类型擦除的存在有关，包括：
泛型类并没有自己独有的Class类对象。比如并不存在List<String>.class或是List<Integer>.class，而只有List.class。
静态变量是被泛型类的所有实例所共享的。对于声明为MyClass<T>的类，访问其中的静态变量的方法仍然是 MyClass.myStaticVar。不管是通过new MyClass<String>还是new MyClass<Integer>创建的对象，都是共享一个静态变量。
泛型的类型参数不能用在Java异常处理的catch语句中。因为异常处理是由JVM在运行时刻来进行的。由于类型信息被擦除，JVM是无法区分两个异常类型MyException<String>和MyException<Integer>的。对于JVM来说，它们都是 MyException类型的。也就无法执行与异常对应的catch语句。
类型擦除的基本过程也比较简单，首先是找到用来替换类型参数的具体类。这个具体类一般是Object。如果指定了类型参数的上界的话，则使用这个上界。把代码中的类型参数都替换成具体的类。同时去掉出现的类型声明，即去掉<>的内容。比如T get()方法声明就变成了Object get()；List<String>就变成了List。接下来就可能需要生成一些桥接方法（bridge method）。这是由于擦除了类型之后的类可能缺少某些必须的方法。比如考虑下面的代码：

泛型在使用中还有一些规则和限制：
    1、泛型的类型参数只能是类类型（包括自定义类），不能是简单类型。
    2、同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
    3、泛型的类型参数可以有多个。
    4、泛型的参数类型可以使用extends语句，例如<T extends superclass>。习惯上成为“有界类型”。
    5、泛型的参数类型还可以是通配符类型。例如Class<?> classType = Class.forName(Java.lang.String);