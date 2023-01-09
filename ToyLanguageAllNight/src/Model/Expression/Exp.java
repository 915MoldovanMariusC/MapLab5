package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws Exception;
    public String toString();

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws Exception;
}