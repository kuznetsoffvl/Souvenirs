package logic;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DataList implements Comparable{
    private final List<Data> list;
    private int cursor  =-1;

    public DataList(List<Data> list) {
        this.list = list;
    }

    public List<Data> getList() {
        return list;
    }

    private void calibrateCursor(){
        cursor = (cursor <= list.size()-1) ? cursor : 0;
    }

    public Data get(int index) {
        cursor = index;
        calibrateCursor();
        return list.get(cursor);
    }

    public Data getNext() {
        return get(cursor + 1);
    }


    @Override
    public String toString() {

        AtomicInteger i = new AtomicInteger();
        return list.stream().map(x -> "(" + (i.getAndIncrement()) + "): " + x.toString() + "\n").collect(Collectors.joining());
    }

    @Override
    public int compareTo(Object o) {
        return String.valueOf(this.hashCode()).compareTo(String.valueOf(o.hashCode()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataList dataList = (DataList) o;
        return cursor == dataList.cursor && Objects.equals(list, dataList.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, cursor);
    }


}
