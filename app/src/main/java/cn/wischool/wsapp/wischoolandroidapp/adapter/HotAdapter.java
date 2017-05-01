package cn.wischool.wsapp.wischoolandroidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.infobean.HotItemData;
import cn.wischool.wsapp.wischoolandroidapp.widget.CircleTransform;

/**
 * Created by Administrator on 2017/2/25.
 * 热门界面item的Adapter
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {

    private Context context;
    private List<HotItemData> datas;
    private LayoutInflater inflater;

    public HotAdapter(Context context, List<HotItemData> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hot,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HotItemData itemData = datas.get(position);
        //设置头像
        Picasso.with(context)
                .load(itemData.getHeadId())
                .transform(new CircleTransform())
                .into(holder.headImageView);
        holder.nameTextView.setText(itemData.getName());//设置用户名
        holder.keysTextView.setText(itemData.getKeys());//绑定关键词
        holder.contentTextView.setText(itemData.getContent());//设置发表的内容
        List<Integer> photos =  itemData.getPhotos();
        for (int i=0;i<itemData.getPhotoCount();i++){  //设置图片
            Picasso.with(context)
                    .load(photos.get(0))
                    .into(holder.contentImageView);
        }
        holder.likeTextView.setText(itemData.getLike()+"");//设置点赞数
        holder.timeTextView.setText(itemData.getTime());
        if (position == datas.size()-1){
            holder.dividerTextView.setVisibility(View.GONE);
        }
        holder.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemData.isLike()){//点赞之后，图案变红，数目+1
                    Picasso.with(context)
                            .load(R.drawable.hot_like_select)
                            .into(holder.likeImageView);
                    itemData.setLike(true);
                    itemData.setLike(itemData.getLike() + 1);
                    holder.likeTextView.setText(itemData.getLike()+"");
                }else{
                    Picasso.with(context)
                            .load(R.drawable.hot_like1)
                            .into(holder.likeImageView);
                    itemData.setLike(false);
                    itemData.setLike(itemData.getLike() - 1);
                    holder.likeTextView.setText(itemData.getLike()+"");
                }
            }
        });

        holder.focusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView headImageView;
        TextView nameTextView;
        Button focusBtn;
        TextView keysTextView;
        TextView contentTextView;
        ImageView contentImageView;
        ImageView likeImageView;
        TextView likeTextView;
        ImageView commentImageView;
        TextView commentTextView;
        ImageView shareImageView;
        TextView shareTextView;
        TextView timeTextView;
        TextView dividerTextView;

        public MyViewHolder(View view) {
            super(view);
            headImageView = (ImageView) view.findViewById(R.id.headIV);
            nameTextView = (TextView) view.findViewById(R.id.nameTV);
            focusBtn = (Button) view.findViewById(R.id.focusBtn);
            keysTextView = (TextView) view.findViewById(R.id.keyTV);
            contentTextView = (TextView) view.findViewById(R.id.contentTV);
            likeImageView = (ImageView) view.findViewById(R.id.likeIV);
            likeTextView = (TextView) view.findViewById(R.id.likeTV);
            commentImageView = (ImageView) view.findViewById(R.id.commentIV);
            commentTextView = (TextView) view.findViewById(R.id.commentTV);
            shareImageView = (ImageView) view.findViewById(R.id.shareIV);
            shareTextView = (TextView) view.findViewById(R.id.shareTV);
            timeTextView = (TextView) view.findViewById(R.id.timeTV);
            contentImageView = (ImageView) view.findViewById(R.id.contentIV);
            dividerTextView = (TextView) view.findViewById(R.id.dividerTV);

        }
    }
}
