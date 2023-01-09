package Model.Statement;

import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;


public class WhileStatement implements IStmt{

    Exp exp;
    IStmt statement;

    @Override
    public PrgState execute(PrgState state) throws Exception {
        Value value = this.exp.eval(state.getSymTable(), state.getHeap());
        if(!(value.getType() instanceof BoolType)){
            throw new RuntimeException("Condition is not boolean");
        }
        if(((BoolValue)value).getVal()){
            state.getExeStack().push(this);
        }
        state.getExeStack().push(statement);
        return null;
    }

    @Override
    public String toString(){
        return "While(" + exp.toString() + "){" + statement.toString() + "};";
    }

}
