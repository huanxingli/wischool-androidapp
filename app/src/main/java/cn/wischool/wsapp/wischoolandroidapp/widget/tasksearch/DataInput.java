package cn.wischool.wsapp.wischoolandroidapp.widget.tasksearch;


/**
 * Created by Administrator on 2015/11/4.
 */
public class DataInput {

//    //图片来源于
//    String drawableUrl = Scheme.DRAWABLE.wrap("R.drawable.image");
    private final static String PHOTOPATH1 = "http://radiotray.sourceforge.net/radio.png";
    private final static String PHOTOPATH2 = "http://simpozia.com/pages/images/stories/windows-icon.png";
    private final static String PHOTOPATH3 = "http://www.bandwidthblog.com/wp-content/uploads/2011/11/twitter-logo.png";
    private final static String PHOTOPATH4 = "http://weloveicons.s3.amazonaws.com/icons/100907_itunes1.png";
    public static TaskCellData[] data = new TaskCellData[]{
            new TaskCellData(6,"哈哈宝贝", PHOTOPATH1,4,"广东工业大学龙洞校区","帮忙去上课","2016.01.04-2016-01.04",5,1),
            new TaskCellData(4,"我宿舍黎凯是帅哥", PHOTOPATH2,5,"广东工业大学龙洞校区","帮忙进行期末复习哥哥哥","2016.01.03-2016.01.15",500,2),
            new TaskCellData(5,"明月即使有", PHOTOPATH3,5,"我家宿舍","打扫卫生","2016.01.07-2016-01.25",100,2),
            new TaskCellData(4,"大一军训要下冰雹", PHOTOPATH4,6,"107","帮忙考试做枪手","2016.01.02-2016.01.05",200,1)
    };
}
