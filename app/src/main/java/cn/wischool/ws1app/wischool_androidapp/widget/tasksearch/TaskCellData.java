package cn.wischool.ws1app.wischool_androidapp.widget.tasksearch;

/**
 * Created by Administrator on 2015/11/4.
 */
public class TaskCellData {
    private int rank;
    private String name;
    private String photo;
    private int honour;
    private String honourHeart;
    private String location;
    private String request;
    private String time;
    private int money;
    private int type;

    public TaskCellData(int rank,String name,String photo,int honour,String location,
                        String request,String time,int money,int type){
        this.rank = rank;
        this.name = name;
        this.photo = photo;
        this.honour = honour;
        this.location = location;
        this.request = request;
        this.time = time;
        this.money = money;
        this.type = type;
        initHeart();
    }

    private void initHeart() {
        String str = "";
        for (int i = 0;i<this.honour;i++){
            str +="❤";
        }
        setHonourHeart(str);
    }

    public String getHonourHeart() {
        return honourHeart;
    }

    public void setHonourHeart(String honourHeart) {
        this.honourHeart = honourHeart;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getHonour() {
        return honour;
    }

    public void setHonour(int honour) {
        this.honour = honour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
