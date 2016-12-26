package cn.wischool.ws1app.wischool_androidapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.wischool.ws1app.wischool_androidapp.R;

/**
 * Created by Administrator on 2016/4/17.
 *
 *
 */
public class LogoView extends LinearLayout {

    private String centerText;
    private float centerTextSize;
    private String rightText;
    private float rightTextSize;
    private Drawable d;

    private TextView tvCenter;
    private TextView tvRight;
    private ImageView imageLogo;

    public LogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.logoLayout);
        centerText = ta.getString(R.styleable.logoLayout_centerText);
        centerTextSize = ta.getDimension(R.styleable.logoLayout_centerTextSize, 12);
        rightText = ta.getString(R.styleable.logoLayout_rightText);
        rightTextSize = ta.getDimension(R.styleable.logoLayout_rightTextSize, 12);
        d = ta.getDrawable(R.styleable.logoLayout_logoSrc);
        ta.recycle();
        setResource(context);
    }

    private void setResource(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.publish_logo,this);
        tvCenter = (TextView) findViewById(R.id.publish_content);
        tvRight = (TextView) findViewById(R.id.publish_right);
        imageLogo = (ImageView) findViewById(R.id.publish_logo);

        tvCenter.setText(centerText);
        tvCenter.setTextSize(centerTextSize);
        tvRight.setText(rightText);
        tvRight.setTextSize(rightTextSize);
        imageLogo.setImageDrawable(d);
    }

    public LogoView(Context context) {
        super(context);
    }


}
