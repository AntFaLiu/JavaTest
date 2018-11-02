package Collection;

import java.util.LinkedList;

public class LinkedListStack extends LinkedList {
    public LinkedListStack(){
        super();
    }

    @Override
    public void push(Object o) {
        super.push(o);
    }

    @Override
    public Object pop() {
        return super.pop();
    }

    @Override
    public Object peek() {
        return super.peek();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    public int search(Object o){
        return indexOf(o);
    }
}
