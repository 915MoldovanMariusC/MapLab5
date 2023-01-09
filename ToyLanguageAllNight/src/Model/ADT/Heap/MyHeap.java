package Model.ADT.Heap;

import java.util.HashMap;

public class MyHeap<address, content> implements MyIHeap<address, content> {

    private address nextFree;

    private HashMap<address,content> map;


    public MyHeap(){
        map = new HashMap<address, content>();
    }

    public address getNextFree() {
        return nextFree;
    }

    public void setNextFree(address nextFree) {
        this.nextFree = nextFree;
    }

    @Override
    public void setContent(HashMap<address, content> map) {
        this.map = map;
    }

    @Override
    public HashMap<address, content> getContent() {
        return this.map;
    }

    @Override
    public void add(address address, content content) {
        if(address.equals(0)){
            throw new RuntimeException("BAD ADDRESS");
        }
        map.put(address, content);
    }

    @Override
    public boolean isDefined(address address) {
        return map.get(address) != null;
    }

    @Override
    public content lookup(address address) {
        return map.get(address);
    }

    @Override
    public void update(address address, content content) {
        map.put(address, content);
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        map.forEach(((address, value) -> {
            string.append(address.toString()).append(" --> ").append(value.toString()).append("\n");
        }));

        return string.toString();
    }
}
