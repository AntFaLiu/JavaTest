//反编译的方式讲解i++ 和 ++i的区别

public class Demo {
    public static void main(String[] args) {
        int i = 0;
        int j = 1;
        int k = 2;
        System.out.println(i++);
        System.out.println(i);
        i = i++;
        j = ++j;
        k += 2;   //就没有一个入栈的过程了
//        System.out.println(i+j+k);
    }
}

//前面6条指令分别给i,j,k变量进行赋值，即
//        0: iconst_0—–把常量0入栈
//        1: istore_1——把栈顶元素（0）存入第一个变量i中
//        2: iconst_1—–把常量1入栈
//        3: istore_2—–把栈顶元素（1）存入第二个变量j中
//        4:iconst_2—–把常量2入栈
//        5: istore_3—–把栈顶元素（2）存入第三个变量k中
//
//        接下来的
//        6,7,10对应的代码是i = i++;     也就是i++一共经历了三步
//        6: iload_1——把第一个变量的值压入栈
//        7:iinc 1, 1——第一个变量的值加1
//        10: istore_1——把栈顶元素存入第一个变量i
//
//        11,14,15对应的是j = ++j;
//        11: iinc 2, 1—–把第二个变量j的值加1
//        14: iload_2—-把第二个变量是j的值压入栈
//        15: istore_2—-把栈顶元素存入第二个变量j中
//       16对应的就是k += 2;
//        16: iinc 3, 2—把第三变量的值加二

