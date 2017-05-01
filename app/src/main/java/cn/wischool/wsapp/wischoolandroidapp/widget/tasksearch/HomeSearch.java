package cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.ui.PublishActivity;

/**
 * Created by Administrator on 2015/12/3.
 *
 * "输入任务名称或关键词"那个任务栏
 *
 */
public class HomeSearch extends RelativeLayout {
    private RelativeLayout homeSearch;
    private EditText editTextSearch;
    private ImageView imageInfo;
    private ImageView imagePublish;
    private Context context;

    private TopBarClickListener listener;

    public interface TopBarClickListener{
        public void editClick();
    }

    public void setTopBarOnClickListener(TopBarClickListener listener){
        this.listener = listener;
    }

    public HomeSearch(Context context){
        super(context);
        this.context = context;
        initHomeSearch(context);
    }
    public HomeSearch(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
        initHomeSearch(context);
    }

    private void initHomeSearch(final Context context){
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_home_search,this);
        homeSearch = (RelativeLayout) findViewById(R.id.homeSearch);
        editTextSearch = (EditText) findViewById(R.id.search_edt);
        imageInfo = (ImageView)findViewById(R.id.home_info_image);
        imagePublish = (ImageView)findViewById(R.id.imageView2);

        imagePublish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PublishActivity.class);
                context.startActivity(i);
            }
        });

        editTextSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editClick();
            }
        });
    }



}
