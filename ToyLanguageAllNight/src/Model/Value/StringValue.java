package Model.Value;

import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;

import java.util.Objects;

public class StringValue implements Value {
    String val;

    public StringValue(String v){
        val=v;
    }

    public String getVal() {return val;}

    public Type getType() { return new StringType();}

    public String toString(){
        return val;
    }

    public boolean equals(StringValue other){
        return Objects.equals(other.getVal(), this.getVal());
    }
}
