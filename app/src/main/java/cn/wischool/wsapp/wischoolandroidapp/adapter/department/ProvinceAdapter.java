package cn.wischool.wsapp.wischoolandroidapp.adapter.department;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.model.db.Province;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ProvinceAdapter extends ArrayAdapter {

    private List<Province> provinces = new ArrayList<>();
    public ProvinceAdapter(Context context, int resource,List<Province> provinces) {
        super(context, resource);
        this.provinces = provinces;
    }

    @Override
    public int getCount() {
        return provinces.size();
    }

    @Override
    public String getItem(int position) {
        return provinces.get(position).getProName();
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(provinces.get(position).getProId());
    }
}
