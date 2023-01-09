package Model.ADT.Dictionary;

import java.lang.annotation.Native;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class FileDictionary<Key, Value> implements MyIDictionary<Key, Value>{
    HashMap<Key, Value> dictionary;


    public FileDictionary(){
        this.dictionary = new HashMap<Key, Value>();
    }
    @Override
    public void add(Key key, Value value) {
        dictionary.put(key, value);
    }

    @Override
    public boolean isDefined(Key id) {
        return dictionary.get(id) != null;
    }

    @Override
    public Value lookup(Key id) {
        return dictionary.get(id);
    }

    @Override
    public void update(Key id, Value val) {
        dictionary.put(id, val);
    }

    @Override
    public HashMap<Key, Value> getContent() {
        return this.dictionary;
    }

    @Override
    public Object clone() {
        return dictionary.clone();
    }

    public void remove(Key id){
        dictionary.remove(id);
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        dictionary.forEach(((key, value) -> {
            string.append(key.toString()).append("\n");
        }));

        return string.toString();

    }
}
