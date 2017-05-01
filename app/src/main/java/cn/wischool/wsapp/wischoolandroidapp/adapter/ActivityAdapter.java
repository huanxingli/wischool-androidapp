package cn.wischool.wsapp.wischoolandroidapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.ApplicationUtil;
import cn.wischool.wsapp.wischoolandroidapp.common.ImageUtil;
import cn.wischool.wsapp.wischoolandroidapp.infobean.ActivityItem;
import cn.wischool.wsapp.wischoolandroidapp.infobean.HotItemData;
import cn.wischool.wsapp.wischoolandroidapp.widget.CircleTransform;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ActivityItem> datas;
    private int[] icons = {R.drawable.head1,R.drawable.head2,R.drawable.head3,R.drawable.head4};

    public ActivityAdapter(Context context,List<ActivityItem> datas){
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_activity,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ActivityItem itemData = datas.get(position);
        //加载每一项的背景图
//        holder.background.setBackground(new BitmapDrawable(context.getResources(),
//               ImageUtil.decodeSampledBitmapFromResource(context.getResources(),itemData.getDrawable(),
//                        ApplicationUtil.getScreenWidth(context),ApplicationUtil.dip2px(200))));
        holder.background.setBackground(new BitmapDrawable(context.getResources(),
                ImageUtil.getBitmap(context,itemData.getDrawable())));
        holder.titleTextView.setText("#" + itemData.getTitle() + "#");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ApplicationUtil.dip2px(30),ApplicationUtil.dip2px(30));
        params.setMargins(10,0,10,0);
        for (int i=0;i<4;i++){
            ImageView view = new ImageView(context);
            view.setLayoutParams(params);
            Picasso.with(context)
                    .load(icons[i])
                    .transform(new CircleTransform())
                    .into(view);
            holder.linearLayout.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout background;
        TextView titleTextView;
        TextView numberTextView;
        LinearLayout linearLayout;
        TextView divider;

        public MyViewHolder(View view) {
            super(view);
            background = (LinearLayout) view.findViewById(R.id.community_activity);
            titleTextView = (TextView) view.findViewById(R.id.titleTV);
            numberTextView = (TextView) view.findViewById(R.id.numberTV);
            linearLayout = (LinearLayout) view.findViewById(R.id.iconLayout);
        }
    }
}
