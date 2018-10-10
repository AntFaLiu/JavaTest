public class WhatWillHappen {

    private static  boolean ready;
    private static  int number;

    private static class NoNameThread extends Thread{
        public void run(){
            while(!ready){
                //do nothing here
            }
            System.out.println(number);
            if(number == 0){
                System.out.println(number);
            }
        }
    }

    public static void main(String args[]){
        new NoNameThread().start();
        number = 42;
        ready = true;
    }
}
