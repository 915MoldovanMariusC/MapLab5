package Model.ADT.Stack;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack(){
        this.stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i = stack.size() - 1; i >= 0; i--){
            string.append(stack.get(i).toString()).append('\n');
        }
        return string.toString();
    }
}