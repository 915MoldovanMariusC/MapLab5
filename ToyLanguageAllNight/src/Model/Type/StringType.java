package Model.Type;

import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    public boolean equals(Object another){
        if (another instanceof  StringType)
            return true;
        else
            return false;
    }
    public String toString() { return "string";}

    @Override
    public Value defaultValue(){
        return new StringValue("");
    }
}
