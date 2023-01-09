package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;

public interface IStmt {
    public PrgState execute(PrgState state) throws Exception;       //which is the execution method for a statement.         }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception;
}
