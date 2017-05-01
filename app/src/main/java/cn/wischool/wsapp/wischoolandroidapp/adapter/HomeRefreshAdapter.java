package cn.wischool.wsapp.wischoolandroidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.UiHelper;
import cn.wischool.wsapp.wischoolandroidapp.infobean.HomeBean;

/**
 * Created by Administrator on 2016/12/29.
 */

public class HomeRefreshAdapter extends BaseAdapter {

    private LinkedList<HomeBean> mListItems;
    private Context context;
    private LayoutInflater inflater;

    public HomeRefreshAdapter(LinkedList<HomeBean> listItems, Context context){
        this.mListItems = listItems;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HomeBean homeBean = mListItems.get(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.home_reflesh_item,parent,false);
            viewHolder.kindTextView = (TextView) convertView.findViewById(R.id.home_item_kind);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.home_item_image);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.home_item_title);
            viewHolder.statusTextView = (TextView) convertView.findViewById(R.id.home_item_status);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.home_item_time);
            viewHolder.placeTextView = (TextView) convertView.findViewById(R.id.home_item_place);
            viewHolder.prizeTextView = (TextView) convertView.findViewById(R.id.home_item_prize);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (homeBean.getKind().equals("0")){
            viewHolder.kindTextView.setBackgroundResource(R.color.homeItemYellow);
        }else{
            viewHolder.kindTextView.setBackgroundResource(R.color.homeItemRed);
        }
        viewHolder.titleTextView.setText(homeBean.getTitle());
        viewHolder.statusTextView.setText(homeBean.getStatus());
        viewHolder.dateTextView.setText(homeBean.getDate());
        viewHolder.placeTextView.setText(homeBean.getPlace());
        viewHolder.prizeTextView.setText("¥" + homeBean.getPrize());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiHelper.getInstance().toastMessage(context,"你点击了" + position);
            }
        });

        return convertView;
    }

    class ViewHolder{
        private TextView kindTextView;
        private ImageView imageView;
        private TextView titleTextView;
        private TextView statusTextView;
        private TextView dateTextView;
        private TextView placeTextView;
        private TextView prizeTextView;
    }
}
