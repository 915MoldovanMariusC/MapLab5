package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public String toString(){
        return type.toString() + name;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Value> dictionary = state.getSymTable();
        if(dictionary.isDefined(name)){
            throw new Exception("Var already declared!");
        }
//        if(type.equals(new BoolType())){
//            Value val = new BoolValue(false);
//            dictionary.add(name, val);
//        }
//        else if(type.equals(new IntType())){
//            Value val = new IntValue(0);
//            dictionary.add(name, val);
//        }else
//            throw new Exception("Invalid type");
        Value val = type.defaultValue();
        dictionary.add(name, val);
        return null;
    }


    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        typeEnv.add(name,type);
        return typeEnv;
    }

}