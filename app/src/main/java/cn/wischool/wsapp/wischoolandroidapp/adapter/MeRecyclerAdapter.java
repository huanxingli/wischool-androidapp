package cn.wischool.wsapp.wischoolandroidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.infobean.PersonItemData;

/**
 * Created by lihuanxing on 2016/4/7.
 */
public class MeRecyclerAdapter extends RecyclerView.Adapter<MeRecyclerAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater mInflater;
    private List<PersonItemData> lists = new ArrayList<PersonItemData>();

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOItemClickListener = listener;
    }

    public MeRecyclerAdapter(Context context, List<PersonItemData> lists) {
        this.context = context;
        this.lists = lists;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.person_info_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        PersonItemData data = lists.get(position);
        holder.logo.setImageResource(data.getLogoId());
        holder.textBig.setText(data.getTextBig());
        holder.textSmall.setText(data.getTextSmall());

        if (mOItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOItemClickListener.onItemClick(holder.itemView,position);

                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOItemClickListener.onItemLongClick(holder.itemView,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView logo;
        TextView textBig;
        TextView textSmall;
        public MyViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.person_item_image);
            textBig = (TextView) itemView.findViewById(R.id.person_item_textbig);
            textSmall = (TextView) itemView.findViewById(R.id.person_item_textsmall);
        }
    }
}
