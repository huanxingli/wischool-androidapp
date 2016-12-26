package cn.wischool.ws1app.wischool_androidapp.adapter.department;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.model.db.College;

/**
 * Created by Administrator on 2016/2/29.
 */
public class CollegeSelectAdapter extends ArrayAdapter {

    private List<College> list = new ArrayList<>();

    public CollegeSelectAdapter(Context context, int resource,List<College> list) {
        super(context, resource);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position).getColName();
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(list.get(position).getColId());
    }
}
