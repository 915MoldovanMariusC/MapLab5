package Model.ADT.List;

import java.util.ArrayList;

public interface MyIList<E>{
    public void add(E elem);
    public E get(int index);

    public String toString();

    public ArrayList<E> getList();
}
