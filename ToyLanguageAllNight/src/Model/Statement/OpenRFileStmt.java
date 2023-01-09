package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.beans.Expression;
import java.io.BufferedReader;
import java.io.FileReader;


public class OpenRFileStmt implements IStmt {
    Exp exp;

    public OpenRFileStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "openRFile(" + exp + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> dictionary = state.getSymTable();
        Value value = this.exp.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue val = (StringValue) value;
            if (!state.getFileTable().isDefined(val.getVal())) {
                BufferedReader reader = new BufferedReader(new FileReader(((StringValue) value).getVal()));
                state.getFileTable().add(((StringValue) value).getVal(), reader);
            } else {
                throw new Exception("File already open");
            }
        } else {
            throw new Exception("Expression is not a string");
        }

        return null;
    }
    //.....
}





//if(symTbl.isDefined(id)){
//            if(symTbl.lookup(id).getType().equals(new RefType(((RefType)symTbl.lookup(id).getType()).getInner()))){
//                Value val = exp.eval(symTbl);
//                RefValue variableValue = (RefValue)symTbl.lookup(id);
//                if(val.getType().equals(variableValue.getLocationType())){
//                    state.getHeap().add(state.getHeap().getNextFree(), val);
//                    state.getHeap().setNextFree(state.getHeap().getNextFree() + 1);
//                    state.getSymTable().update(id, new RefValue(state.getHeap().getNextFree() - 1, ));
//                }
//
//            }
//        }