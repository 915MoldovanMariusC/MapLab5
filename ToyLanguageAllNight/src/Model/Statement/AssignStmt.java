package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.Stack.MyIStack;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;
    ///....

    public AssignStmt(String id, Exp expression){
        this.id = id;
        this.exp = expression;
    }

    public String toString(){ return id+"="+ exp.toString();}

    public PrgState execute(PrgState state) throws Exception{
        MyIStack<IStmt> stk=state.getExeStack();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer, Value> hp = state.getHeap();
        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl, hp);
            Type typId= (symTbl.lookup(id)).getType();
            if ((val.getType()).equals(typId))
                symTbl.update(id, val);
            else throw new Exception("declared type of variable"+id+" and type of  the assigned expression do not match");}

        else throw new Exception("the used variable" +id + " was not declared before");
        return null;    }
        //...

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else throw new Exception("Assignment: right hand side and left hand side have different types ");  }

}