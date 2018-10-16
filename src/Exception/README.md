# Java  异常：
## Throwable 的子类 ： 
    error 和 exception 二者都是异常处理的重要子类
    Error ：程序无法处理的错误： 例如：java 虚拟机错误  类定义错误   Error类一般是指与虚拟机相关的问题，如系统崩溃，虚拟机错误，内存空间不足，方法调用栈溢出等
    列出一些你常见的运行时异常？
    答：
    - ArithmeticException（算术异常）
    - ClassCastException （类转换异常）
    - IllegalArgumentException （非法参数异常）
    - IndexOutOfBoundsException （下标越界异常）
    - NullPointerException （空指针异常）
    - SecurityException （安全异常） 
    
    运行时异常：都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
    运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，即使没有用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。
    非运行时异常（编译异常）：是RuntimeException以外的异常，类型上都属于Exception类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。

## 4.throw关键字：语句抛出异常   throws关键字：声明异常（方法抛出一个异常）
    01.throw是语句抛出一个异常。
    语法：throw (异常对象);
    throw e;
    02.throws 是方法可能抛出异常的声明。(用在声明方法时，表示该方法可能要抛出异常)   调用者必须做出处理（捕获或继续抛出）
    语法：[(修饰符)](返回值类型)(方法名)([参数列表])[throws(异常类)]{......}
    public void doA(int a) throws Exception1,Exception3{......}
    03.throws可以单独使用，但throw不能， throw要么和try-catch-finally语句配套使用，要么与throws配套使用。但throws可以单独使 用，然后再由处理异常的方法捕获。
    04.throw语句用在方法体内,表示抛出异常,由方法体内的语句处理 
       throws语句用在方法声明后面,表示再抛出异常,由调用这个方法的上一级方法中的语句来处理，必须做出处理(捕获或继续声明)
    05.throws主要是声明这个方法会抛出这种类型的异常，使其他地方调用它时知道要捕获这个异常，使得提醒必须做出处理。否则编译是不会通过的。
         throw是具体向外抛异常的动作，所以它是抛出一个异常实例。\
## finalize()在什么时候被调用?
    有三种情况
    1.所有对象被Garbage Collection时自动调用,比如运行System.gc()的时候.
    2.程序退出时为每个对象调用一次finalize方法。
    3.显式的调用finalize方法