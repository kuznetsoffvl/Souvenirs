package logic;

import java.util.List;

public class ManufacturerList implements Data{
    private List<Data> list;
    private int cursor  =-1;

    public ManufacturerList(List<Data> list) {
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
    public String getName() {
        return get(cursor).getName();
    }
}
