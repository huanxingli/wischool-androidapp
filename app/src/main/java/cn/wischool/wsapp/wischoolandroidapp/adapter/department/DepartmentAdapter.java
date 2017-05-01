package cn.wischool.wsapp.wischoolandroidapp.adapter.department;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.model.db.Department;

/**
 * Created by Administrator on 2016/3/6.
 */
public class DepartmentAdapter extends ArrayAdapter {

    private List<Department> list;

    public DepartmentAdapter(Context context, int resource,List<Department> list) {
        super(context, resource);
        this.list = list;
    }

    @Override
    public String getItem(int position) {
        return list.get(position).getDepName();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }
}
