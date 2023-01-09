package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.Objects;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    //    ....

    public ArithExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        if (Objects.equals(op, "+")) {
            this.op = 1;
        } else if (Objects.equals(op, "-")) {
            this.op = 2;
        } else if (Objects.equals(op, "*")) {
            this.op = 3;
        } else if(Objects.equals(op, "/")){
            this.op = 4;
        }
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws Exception {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0) throw new Exception("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new Exception("second operand is not an integer");
        } else
            throw new Exception("first operand is not an integer");
        System.out.println("ERROR LA EVAL ARITHMETIC");
        return null;
    }


    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws Exception{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())) {

            if(typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new Exception("second operand is not an integer");}
        else throw new Exception("first operand is not an integer");
    }


}
