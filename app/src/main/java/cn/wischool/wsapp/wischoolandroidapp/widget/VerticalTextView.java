package cn.wischool.wsapp.wischoolandroidapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by lihuanxing on 2017/1/4.
 * 自动轮播的垂直textView
 */

public class VerticalTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private static final int FLAG_START_AUTO_SCROLL = 0;
    private static final int FLAG_STOP_AUTO_SCROLL = 1;

    private Context mContext;
    private ArrayList<String> mLists; //textView的所有信息
    private int currentId = 0;
    private Handler handler;

    private float mTextSize = 16 ;//默认的字体大小
    private int mPadding = 5; //textView的间隔
    private int textColor = Color.BLACK; //字体颜色

    public VerticalTextView(Context context) {
        this(context,null);
        mContext = context;
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mLists = new ArrayList<String>();
    }

    /**
     * 设置跳转的时间
     * @param animDuration
     */
    public void setAnimTime(long animDuration) {
        setFactory(this);
        Animation in = new TranslateAnimation(0, 0, (mPadding+mTextSize)*5, 0);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, -(mPadding+mTextSize)*5);
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        setInAnimation(in);
        setOutAnimation(out);
    }


    /**
     * @param textSize 字号
     * @param padding 内边距
     * @param textColor 字体颜色
     */
    public void setText(float textSize,int padding,int textColor) {
        mTextSize = textSize;
        mPadding = padding;
        this.textColor = textColor;
    }

    /**
     * 间隔时间
     * @param time
     */
    public void setTextStillTime(final long time){
        handler =new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_START_AUTO_SCROLL:
                        if (mLists.size() > 0) {
                            currentId++;
                            setText(mLists.get(currentId % mLists.size()));
                        }
                        handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL,time);
                        break;
                    case FLAG_STOP_AUTO_SCROLL:
                        handler.removeMessages(FLAG_START_AUTO_SCROLL);
                        break;
                }
            }
        };
    }


    /**
     * 开始滚动
     */
    public void startAutoScroll() {
        handler.sendEmptyMessage(FLAG_START_AUTO_SCROLL);
    }

    public void stopAutoScroll(){
        handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
    }

    public void setTextLists(ArrayList<String> lists){
        mLists.clear();
        mLists.addAll(lists);
        currentId = -1;
    }


    @Override
    public View makeView() {
        TextView textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setMaxLines(1);
        textView.setPadding(mPadding, mPadding, mPadding, mPadding);
        textView.setTextColor(textColor);
        textView.setTextSize(mTextSize);

        textView.setClickable(true);

        return textView;
    }
}
