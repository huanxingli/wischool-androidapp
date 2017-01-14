package cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.BannerManager;
import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.adapter.HomeRefreshAdapter;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.infobean.Banner;
import cn.wischool.ws1app.wischool_androidapp.infobean.HomeBean;
import cn.wischool.ws1app.wischool_androidapp.ui.departmentSelect.ProvinceSlectActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.PublishActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.TaskSearchActivity;
import cn.wischool.ws1app.wischool_androidapp.widget.VerticalTextView;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class HomeFragment extends Fragment {

    private Button button;
    private ImageView imagePublish;
    private EditText editText;
    private Button btnTest;
    private RelativeLayout rlContainer;
    private BannerManager mBanner;
    private LinkedList<HomeBean> mListItems;
    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;

    private VerticalTextView verticalTextView;

    private HomeRefreshAdapter mAdapter;

    private int mItemCount = 4;//测试的时候默认只有4个item

    private ArrayList<String> newsLists;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        editText = (EditText) view.findViewById(R.id.search_edt);
//        button = (Button) view.findViewById(R.id.btn);
        imagePublish = (ImageView) view.findViewById(R.id.imageView2);
//        btnTest = (Button) view.findViewById(R.id.home_btn_test);
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
        verticalTextView = (VerticalTextView) view.findViewById(R.id.home_text_auto);

        rlContainer = (RelativeLayout) view.findViewById(R.id.rl_container);
        Drawable drawable = getResources().getDrawable(R.drawable.home_search);
        drawable.setBounds(0,0,20,20);//距离左边，上边，宽度和高度
        editText.setCompoundDrawables(drawable,null,null,null);


        mBanner = BannerManager.getInstance();
        mBanner.initView(getContext(),rlContainer).attachViewPager(R.id.vp_ad).setLocalBanner(getLocalBanner()).startLoop(true);

        //初始化滑动的视图并随时改变
        initViews();

        //初始化数据
        initDatas();

        verticalTextView.setTextLists(newsLists);//加入显示内容,集合类型
        verticalTextView.setText(11, 0, Color.GRAY);//设置属性,具体跟踪源码
        verticalTextView.setTextStillTime(5000);//设置停留时长间隔
        verticalTextView.setAnimTime(1000);//设置进入和退出的时间间隔

        //task Item设置适配器
        mAdapter = new HomeRefreshAdapter(mListItems,getContext());
        mPullRefreshListView.setAdapter(mAdapter);

        setListViewHeightBasedOnChildren(mPullRefreshListView);

        //下拉样式初始化
        initIndicator();


        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        verticalTextView.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        verticalTextView.stopAutoScroll();
    }

    private void setListener() {

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferencesHelper.putBoolean(getActivity().getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", false);
////				Province p = provinceService.getProvinceById("12");
////				if (p != null) {
////					UiHelper.getInstance().toastMessage(HomeActivity.this, p.getProId() + ":" + p.getProName());
////				} else {
////					UiHelper.getInstance().toastMessage(HomeActivity.this, "没有该数据");
////				}
//            }
//        });

//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ProvinceSlectActivity.class);
//                startActivity(intent);
//            }
//        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TaskSearchActivity.class);
                startActivity(i);
            }
        });


        // 设置监听事件
        mPullRefreshListView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
                {
                    @Override
                    public void onRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        String label = DateUtils.formatDateTime(
                                getContext(),
                                System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME
                                        | DateUtils.FORMAT_SHOW_DATE
                                        | DateUtils.FORMAT_ABBREV_ALL);
                        // 显示最后更新的时间
                        refreshView.getLoadingLayoutProxy()
                                .setLastUpdatedLabel(label);

                        // 模拟加载任务
                        new GetDataTask().execute();
                    }
                });
    }

    private List<Banner> getLocalBanner(){
        List<Banner> localBanners = new ArrayList<>();
        Banner banner1 = new Banner();
        banner1.setId(R.drawable.i1);
        banner1.setLink("1");

        Banner banner2 = new Banner();
        banner2.setId(R.drawable.i2);
        banner2.setLink("2");

        Banner banner3 = new Banner();
        banner3.setId(R.drawable.i3);
        banner3.setLink("3");

        Banner banner4 = new Banner();
        banner4.setId(R.drawable.i4);
        banner4.setLink("4");

        localBanners.add(banner1);
        localBanners.add(banner2);
        localBanners.add(banner3);
        localBanners.add(banner4);

        return localBanners;
    }

    public void initViews(){

        editText.setFocusable(false);

    }

    private void initIndicator() {
        ILoadingLayout startLabels = mPullRefreshListView
                .getLoadingLayoutProxy();
        startLabels.setPullLabel("你可劲拉，拉...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("好嘞，正在刷新...");// 刷新时
        startLabels.setReleaseLabel("你敢放，我就敢刷新...");// 下来达到一定距离时，显示的提示
    }


    public void setListViewHeightBasedOnChildren(PullToRefreshListView listView) {
        // 获取ListView对应的Adapter
        if (mAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = mAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = mAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerPadding()
                * (mAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private void initDatas() {
        //初始化item数据和数据源
        mListItems = new LinkedList<HomeBean>();
        for (int i = 0; i < mItemCount; i++) {
            HomeBean homeBean = new HomeBean("0","","中午11点高场中通快递，送至" + i + "区","等待中","2017.01.04",
                    "广工龙洞校区" + i + "区",2);
            mListItems.add(homeBean);
        }

        //初始化新闻数据
        newsLists = new ArrayList<>();
        newsLists.add("B区13号水管进行维修,将在13点-15点进行停水，请同学做好储水准备");
        newsLists.add("2017年京东校园招聘将在本月5号在龙洞校区103召开宣讲会");
        newsLists.add("2017年上学期热水卡补贴活动正在开始中");
        newsLists.add("本学期的寒假具体放假时间是：1月5号-2月20号，请广大师生做好准备");


    }

    private class GetDataTask extends AsyncTask<Void, Void, HomeBean> {

        @Override
        protected HomeBean doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return new HomeBean("1","","我是新增的服务","等待中","2017.01.05",
                    "广工龙洞校区G区",3);
        }

        @Override
        protected void onPostExecute(HomeBean result) {
            mListItems.addFirst(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

}
