package Model.Statement;

import Model.Expression.Exp;
import Model.Expression.VarExp;
import Model.PrgState.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class ReadFileStmt implements IStmt {
    Exp exp;
    String id;

    public ReadFileStmt(Exp exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    public String toString(){ return "Read(" + exp.toString() + "," + id + ")";}

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if (state.getSymTable().isDefined(id)) {
            Type idType = state.getSymTable().lookup(id).getType();
            if (idType.equals(new IntType())) {
                Value value = exp.eval(state.getSymTable(), state.getHeap());
                if (value.getType().equals(new StringType())) {
                    BufferedReader reader = state.getFileTable().lookup(((StringValue) value).getVal());
                    if (reader != null) {
                        String readString = reader.readLine();
                        IntValue readInteger;
                        if (readString == null) {
                            readInteger = new IntValue(0);
                        } else {
                            readInteger = new IntValue(Integer.parseInt(readString));
                        }
                        state.getSymTable().update(id, readInteger);
                    } else {
                        throw new Exception("FILE IS NOT DEFINED");
                    }
                } else {
                    throw new Exception("Provided expression is not a string");
                }
            } else {
                throw new Exception("Provided variable is not an integer");
            }
        } else {
            throw new Exception("Provided variable does not exist");
        }

        return null;
    }
}
