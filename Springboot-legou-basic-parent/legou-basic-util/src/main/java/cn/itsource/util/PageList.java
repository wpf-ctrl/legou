package cn.itsource.util;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    private  long totle=0;
    private List<T> rows=new ArrayList<T>();

    public PageList() {
    }

    public PageList(long totle, List<T> rows) {
        this.totle = totle;
        this.rows = rows;
    }

    public long getTotle() {
        return totle;
    }

    public void setTotle(long totle) {
        this.totle = totle;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
