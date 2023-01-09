package Model.ADT.List;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MyList<E> implements MyIList<E> {
    ArrayList<E> list;
    int length = 0;

    public MyList(){
        this.list = new ArrayList<E>();
    }



    public MyList(List<E> list){
        this.list = (ArrayList<E>) list;
    }
    @Override
    public void add(E elem){
        list.add(elem);
        length++;
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        list.forEach((value) -> string.append(value.toString()).append("\n"));
        return string.toString();
    }

    @Override
    public ArrayList<E> getList() {
        return list;
    }
}
