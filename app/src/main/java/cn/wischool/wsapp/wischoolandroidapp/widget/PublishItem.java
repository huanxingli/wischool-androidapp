package cn.wischool.wsapp.wischoolandroidapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.wischool.wsapp.wischoolandroidapp.R;

/**
 * Created by Administrator on 2016/4/20.
 */
public class PublishItem extends LinearLayout {

    private ImageView image;
    private TextView textView;

    private Drawable d;
    private String info;

    public PublishItem(Context context) {
        super(context);
    }

    public PublishItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.publishItem);
        info = ta.getString(R.styleable.publishItem_text);
        d = ta.getDrawable(R.styleable.publishItem_src);
        ta.recycle();
        setResource(context);
    }

    private void setResource(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.publish_item,this);
        image = (ImageView) findViewById(R.id.publish_item_image);
        textView = (TextView)findViewById(R.id.publish_item_content);

        image.setImageDrawable(d);
        textView.setText(info);
    }

    public void setText(String str){
        textView.setText(str);
    }

}
