package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first,IStmt second){
        this.first = first;
        this.snd = second;
    }

    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
    public PrgState execute(PrgState state)  throws Exception{
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;    }


    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return snd.typecheck(first.typecheck(typeEnv));}
}