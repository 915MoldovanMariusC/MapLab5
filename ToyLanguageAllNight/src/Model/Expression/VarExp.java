package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Exp{
    String id;
    //....

    public VarExp(String id){
        this.id = id;
    }
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp)  throws Exception {
        return tbl.lookup(id);
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        return typeEnv.lookup(id);
    }


}