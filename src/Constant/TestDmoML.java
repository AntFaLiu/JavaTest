package constant;

import java.util.Scanner;

public class TestDmoML {
    public static int Get_Prio(char opera, boolean instack) {
        int prio = Constant.OPERATORS_PRIO_ERROR;
        if (instack) {
            switch (opera) {
                case '+':
                    prio = Constant.OPERATORS_PRIO_PLUS_IN;
                    break;
                case '-':
                    prio = Constant.OPERATORS_PRIO_SUB_IN;
                    break;
                case '*':
                    prio = Constant.OPERATORS_PRIO_MULTY_IN;
                    break;
                case '/':
                    prio = Constant.OPERATORS_PRIO_DIV_IN;
                    break;
                case '(':
                    prio = Constant.OPERATORS_PRIO_LEFT_BRAK_IN;
                    break;
                default:
                    prio = Constant.OPERATORS_PRIO_ERROR;
                    break;
            }
        } else {
            switch (opera) {
                case '+':
                    prio = Constant.OPERATORS_PRIO_PLUS_OUT;
                    break;
                case '-':
                    prio = Constant.OPERATORS_PRIO_SUB_OUT;
                    break;
                case '*':
                    prio = Constant.OPERATORS_PRIO_MULTY_OUT;
                    break;
                case '/':
                    prio = Constant.OPERATORS_PRIO_DIV_OUT;
                    break;
                case '(':
                    prio = Constant.OPERATORS_PRIO_LEFT_BRAK_OUT;
                    break;
                case ')':
                    prio = Constant.OPERATORS_PRIO_RIGHT_BRAK_OUT;
                    break;
                default:
                    prio = Constant.OPERATORS_PRIO_ERROR;
                    break;
            }
        }
        return prio;
    }


    public static void MiddleToLast(char[] strLast, String strMid) {
        char stack[] = new char[strMid.length()];
        int top = 0;

        int PrioIn;
        int PrioOut;
        int len = strMid.length();
        int i = 0;//strMid下标
        int j = 0;//strLast下标
        while (i != len) {
            if (Character.isDigit(strMid.charAt(i))) {//判断是否为数字
                strLast[j++] = strMid.charAt(i);
                i++;
            } else {
                if (top == 0) {  //说明stack为空也就是存放操作符的为空·
                    stack[top++] = strMid.charAt(i);
                    i++;
                } else {
                    //true 表示栈内元素  false 表示栈外元素
                    PrioIn = Get_Prio(stack[top - 1], true);
                    PrioOut = Get_Prio(strMid.charAt(i), false);

                    if (PrioOut < PrioIn)//栈外优先级高 入栈·
                    {
                        stack[top++] = strMid.charAt(i);
                        i++;
                    } else if (PrioOut == PrioIn) {//左右括号是否匹配
                        top--;
                        i++;
                    } else {//栈外优先级低 出栈
                        strLast[j++] = stack[--top];
                    }
                }
            }
        }

        while (top > 0)//栈内还有运算符
        {
            strLast[j++] = stack[--top];
        }
    }


    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("请输入中缀表达式:");
            String strMid = in.nextLine();
            char[] strLast = new char[strMid.length()];   //初始化一个字节数组
            MiddleToLast(strLast, strMid);
            for (char c : strLast) {
                if(c != 0){
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }
}
