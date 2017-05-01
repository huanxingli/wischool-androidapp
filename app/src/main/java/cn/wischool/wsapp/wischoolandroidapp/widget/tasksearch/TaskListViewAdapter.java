package cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.ui.TaskDetailActivity;

/**
 * Created by Administrator on 2015/11/4.
 */
public class TaskListViewAdapter extends BaseAdapter {

    private Context context;
    private List<TaskCellData> list = new ArrayList<>();

    public TaskListViewAdapter(Context context,List<TaskCellData> list){
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TaskCellData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        TaskCellData taskCellData = list.get(position);
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                   .inflate(R.layout.task_list_cell_layout2, null);
            viewHolder.textRank = (TextView) convertView.findViewById(R.id.list_text_rank);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.list_text_name);
            viewHolder.btnSure = (Button) convertView.findViewById(R.id.list_btn_accept);
            viewHolder.imagePhoto = (ImageView) convertView.findViewById(R.id.list_image_touxiang);
            viewHolder.textHonour = (TextView) convertView.findViewById(R.id.list_text_honour);
            viewHolder.textLocation = (TextView) convertView.findViewById(R.id.list_text_location);
            viewHolder.textRequest = (TextView) convertView.findViewById(R.id.list_text_request);
            viewHolder.textTime = (TextView) convertView.findViewById(R.id.list_text_time);
            viewHolder.textMoney = (TextView) convertView.findViewById(R.id.list_text_money);
            viewHolder.textType = (TextView) convertView.findViewById(R.id.list_task_type);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.setBackgroundResource(R.drawable.listview_selector);
        viewHolder.textRank.setText("Lv." + taskCellData.getRank());
        viewHolder.textName.setText(taskCellData.getName());

        Picasso.with(this.context).load(taskCellData.getPhoto()).into(viewHolder.imagePhoto);

        viewHolder.textHonour.setText(taskCellData.getHonourHeart());
        viewHolder.textLocation.setText("地点:" + taskCellData.getLocation());
        viewHolder.textRequest.setText("要求:" + taskCellData.getRequest());
        viewHolder.textTime.setText("时间:" + taskCellData.getTime());
        viewHolder.textMoney.setText("￥ " + taskCellData.getMoney() + "元");
        viewHolder.btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TaskDetailActivity.class);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        if (taskCellData.getType()==1)
            viewHolder.textType.setBackgroundColor(android.graphics.Color.parseColor("#ffcd34"));
        else{
            viewHolder.textType.setBackgroundColor(Color.RED);
        }
        return convertView;
    }

    class ViewHolder{
        public TextView textRank;
        public TextView textName;
        public Button btnSure;
        public ImageView imagePhoto;
        public TextView textHonour;
        public TextView textLocation;
        public TextView textRequest;
        public TextView textTime;
        public TextView textMoney;
        public TextView textType;
    }

}
