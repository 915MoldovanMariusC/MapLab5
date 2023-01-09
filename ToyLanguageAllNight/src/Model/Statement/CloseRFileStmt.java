package Model.Statement;

import Model.ADT.Heap.MyIHeap;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class CloseRFileStmt implements IStmt{

    Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() { return "Close(" + exp.toString() + ")";}

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIHeap<Integer, Value> hp = state.getHeap();
        Value value = exp.eval(state.getSymTable(), hp);
        if(value.getType().equals(new StringType())){
            BufferedReader reader = state.getFileTable().lookup(((StringValue) value).getVal());
            if(reader != null){
                reader.close();
                state.getFileTable().remove(((StringValue) value).getVal());
            }else{
                throw new Exception("File not defined in file table");
            }
        } else{
            throw new Exception("Provided expression is not a string");
        }

        return null;
    }
}
