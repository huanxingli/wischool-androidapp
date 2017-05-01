package cn.wischool.wsapp.wischoolandroidapp.infobean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */

public class HotItemData {
    private int headId;
    private String name;
    private String keys;
    private String content;
    private int photoCount;
    private List<Integer> photos;
    private boolean isLike;

    private int like;
    private String time;


    public HotItemData(int headId, String name, String keys, String content, int photoCount, List<Integer> photos, boolean isLike, int like, String time) {
        this.headId = headId;
        this.name = name;
        this.keys = keys;
        this.content = content;
        this.photoCount = photoCount;
        this.photos = photos;
        this.isLike = isLike;
        this.like = like;
        this.time = time;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }

    public HotItemData() {
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
