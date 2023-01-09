package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;
    ///....
    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp=e;
        thenS=t;
        elseS=el;
    }
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+"))";
    }

    public PrgState execute(PrgState state) throws Exception{
        ///.....
        BoolValue val = (BoolValue) exp.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStmt> stk = state.getExeStack();
        if(val.getVal()){
            stk.push(thenS);
        }
        else
            stk.push(elseS);
    return null;    }
    ///...

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            //thenS.typecheck(typeEnv.clone());
            //elseS.typecheck(clone(typeEnv));
        return typeEnv;
        }
        else throw new Exception("The condition of IF has not the type bool");   }

}