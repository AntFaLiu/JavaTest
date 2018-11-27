package Constant;

public class Constant {
    public static final int OPERATORS_PRIO_PLUS_IN = 4;  //栈内加法
    public static final int OPERATORS_PRIO_SUB_IN  =  4;   //栈内减法
    public static final int  OPERATORS_PRIO_MULTY_IN  =  2; //栈内乘法
    public static final int OPERATORS_PRIO_DIV_IN  =  2 ;  //栈内除法
    public static final int OPERATORS_PRIO_LEFT_BRAK_IN  =  10;  //栈内左括号

    public static final int OPERATORS_PRIO_PLUS_OUT  =  5 ; //栈外加法
    public static final int OPERATORS_PRIO_SUB_OUT  =   5;   //栈外减法
    public static final int OPERATORS_PRIO_MULTY_OUT  =  3; //栈外乘法
    public static final int OPERATORS_PRIO_DIV_OUT  =  3;   //栈外除法
    public static final int OPERATORS_PRIO_LEFT_BRAK_OUT =  1;  //栈外左括号
    public static final int OPERATORS_PRIO_RIGHT_BRAK_OUT =  10;  //栈外右括号
    public static final int OPERATORS_PRIO_ERROR = -1;
}
