package cn.wischool.wsapp.wischoolandroidapp.infobean;

/**
 * Created by xing on 2016/12/6.
 */

public class Banner {

    /**
     * id : 1
     * title : 第一ming
     * img : http://192.168.1.181:83/public/upload/banner/1468396117_3871.png?v=20160629
     * link : 相当于是tag
     */

    private int id;
    private String title;
    private String img;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
