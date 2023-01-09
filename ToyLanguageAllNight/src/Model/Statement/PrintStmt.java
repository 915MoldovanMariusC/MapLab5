package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }
    public PrgState execute(PrgState state) throws Exception{
        MyIList<Value> out = state.getOut();
        Value val = exp.eval(state.getSymTable(), state.getHeap());
        out.add(val);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}