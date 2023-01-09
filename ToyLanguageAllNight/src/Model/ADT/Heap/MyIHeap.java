package Model.ADT.Heap;

import java.util.HashMap;

public interface MyIHeap<address, content> {

    void add(address address, content content);

    boolean isDefined(address address);

    content lookup(address address);

    void update(address address, content content);

    public address getNextFree();

    public void setNextFree(address nextFree);

    public void setContent(HashMap<address, content> map);

    public HashMap<address, content> getContent();
}
