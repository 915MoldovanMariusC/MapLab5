package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public Type getLocationType() {
        return locationType;
    }

    public void setLocationType(Type locationType) {
        this.locationType = locationType;
    }

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    //..................................
    int getAddr() {return address;}

    public Type getType() { return new RefType(locationType);} }