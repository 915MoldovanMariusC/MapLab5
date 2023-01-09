package Model.ADT.Dictionary;

import Model.Value.Value;

import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<Key, Value> {
    void add(Key key, Value value);

    boolean isDefined(Key id);

    Value lookup(Key id);

    void update(Key id, Value val);

    HashMap<Key, Value> getContent();

    Object clone();
}
