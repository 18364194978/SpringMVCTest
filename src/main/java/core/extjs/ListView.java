package core.extjs;

import java.util.List;
/**
 * Created by xie on 2018/3/2.
 */
public class ListView<E> {
    private int totalRecord;
    private List<E> data;
    public int getTotalRecord(){return totalRecord;}
    public void setTotalRecord(int totalRecord){this.totalRecord = totalRecord;}
    public List<E>getData(){return data;}
    public void setData(List<E> data){this.data = data;}
}
