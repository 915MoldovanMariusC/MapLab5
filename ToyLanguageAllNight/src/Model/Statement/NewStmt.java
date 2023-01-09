package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

import java.sql.Ref;

public class NewStmt implements IStmt {

    String id;
    Exp exp;

    public NewStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {return "new(" + id + "," + exp.toString() + ")";}

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        if (symTbl.isDefined(id)) {
            Value varValue = symTbl.lookup(id);
            if (varValue.getType().equals(((RefType) varValue.getType()).getInner())) {
                Value result = exp.eval(symTbl, heap);
                if (result.getType().equals(((RefValue) varValue).getLocationType())) {
                    heap.setNextFree(heap.getNextFree() + 1);
                    heap.add(heap.getNextFree(), result);
                    RefValue newValue = new RefValue(heap.getNextFree(), ((RefValue) varValue).getLocationType());
                    symTbl.update(id, newValue);
                } else {
                    throw new RuntimeException("Value type and location type are different");
                }
            } else {
                throw new RuntimeException("Type is not RefType");
            }
        } else {
            throw new RuntimeException("Variable is not defined");
        }

        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else throw new Exception("NEW stmt: right hand side and left hand side have different types ");   }

}
