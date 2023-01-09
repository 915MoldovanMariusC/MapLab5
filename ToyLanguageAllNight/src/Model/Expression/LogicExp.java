package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;
    //...

    public LogicExp(int op, Exp e1, Exp e2){
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString(){
        String op = "&&";
        if(this.op == 2)
            op = "||";
        return e1.toString() + op + e2.toString();
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws Exception{
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if(v1.getType().equals(new BoolType())){
            v2 = e2.eval(tbl, hp);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean bool1, bool2;
                bool1 = b1.getVal();
                bool2 = b2.getVal();
                if(op == 1){ // op == 1 AND
                    return new BoolValue(bool1 && bool2);
                }
                return new BoolValue(bool1 || bool2);

            }
            throw new Exception("OP2 IS NOT A BOOL");
        }
        throw new Exception("OP1 IS NOT A BOOL");

    }
    //....

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())) {
            if(typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new Exception("second operand is not a boolean");}
        else throw new Exception("first operand is not a boolean");
    }
}