package cn.wischool.wsapp.wischoolandroidapp.infobean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ActivityItem {
    private String title;
    private int number;
    private int drawable;

    public ActivityItem(String title, int number, int drawable) {
        this.title = title;
        this.number = number;
        this.drawable = drawable;
    }

    public ActivityItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
