package cn.wischool.wsapp.wischoolandroidapp.infobean;

/**
 * Created by Administrator on 2017/1/4.
 * 首页的item的数据项
 */

public class HomeBean {
    private String kind;
    private String imageUrl;
    private String title;
    private String status;
    private String date;
    private String place;
    private int prize;

    public HomeBean(String kind, String imageUrl, String title, String status, String date, String place, int prize) {
        this.kind = kind;
        this.imageUrl = imageUrl;
        this.prize = prize;
        this.place = place;
        this.date = date;
        this.status = status;
        this.title = title;
    }

    public HomeBean(){}

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
