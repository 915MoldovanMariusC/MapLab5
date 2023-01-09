package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;

public class WriteHeapStmt implements IStmt{

    private String id;

    private Exp exp;

    public WriteHeapStmt(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }

    public String toString() { return "writeHeap(" + id + "," + exp.toString() + ")";}

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value value = symTable.lookup(id);
        if(value != null){
            if(value.getType() instanceof RefType){
                Integer address = ((RefValue)value).getAddress();
                Value heapValue = heap.lookup(address);
                if(heapValue != null){
                    Value result = exp.eval(symTable, heap);
                    if(result.getType().equals(((RefValue) value).getLocationType())){
                        heap.update(address, result);
                    } else{
                        throw new RuntimeException("Result type not like location type");
                    }
                } else {
                    throw new RuntimeException("Adress not on heap");
                }
            } else {
                throw new Exception("Variable not of RefType");
            }
        } else {
            throw new Exception("Variable not defined");
        }
        return null;
    }
}
