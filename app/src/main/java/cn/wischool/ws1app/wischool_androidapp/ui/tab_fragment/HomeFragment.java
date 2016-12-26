package cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.BannerManager;
import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.ImageUtil;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.infobean.Banner;
import cn.wischool.ws1app.wischool_androidapp.ui.departmentSelect.ProvinceSlectActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.PublishActivity;
import cn.wischool.ws1app.wischool_androidapp.ui.TaskSearchActivity;

/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class HomeFragment extends Fragment {

    private Button button;

    private ImageView imageHonour;

    private ImageView imageMoney;

    private ImageView imageEmergency;

    private ImageView imageHeart;

    private ImageView imagePublish;

    private EditText editText;

    private Button btnTest;

    private RelativeLayout rlContainer;

    private BannerManager mBanner;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        editText = (EditText) view.findViewById(R.id.search_edt);
        imageHonour = (ImageView) view.findViewById(R.id.image_honour);
        imageMoney = (ImageView) view.findViewById(R.id.image_money);
        imageEmergency = (ImageView) view.findViewById(R.id.image_emergency);
        button = (Button) view.findViewById(R.id.btn);
        imageHeart = (ImageView) view.findViewById(R.id.image_heart);
        imagePublish = (ImageView) view.findViewById(R.id.imageView2);
        btnTest = (Button) view.findViewById(R.id.home_btn_test);

        rlContainer = (RelativeLayout) view.findViewById(R.id.rl_container);
        mBanner = BannerManager.getInstance();
        mBanner.initView(getContext(),rlContainer).attachViewPager(R.id.vp_ad).setLocalBanner(getLocalBanner()).startLoop(true);

        //初始化滑动的视图并随时改变
        initViews();

        setListener();
        return view;
    }

    private void setListener() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesHelper.putBoolean(getActivity().getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", false);
//				Province p = provinceService.getProvinceById("12");
//				if (p != null) {
//					UiHelper.getInstance().toastMessage(HomeActivity.this, p.getProId() + ":" + p.getProName());
//				} else {
//					UiHelper.getInstance().toastMessage(HomeActivity.this, "没有该数据");
//				}
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProvinceSlectActivity.class);
                startActivity(intent);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TaskSearchActivity.class);
                startActivity(i);
            }
        });

        imagePublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PublishActivity.class);
                startActivity(i);
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

        //设置荣誉榜、赏金榜、紧急任务的图案
        imageHonour.setImageBitmap(ImageUtil.getBitmap(getActivity(), R.drawable.home_star));
        imageMoney.setImageBitmap(ImageUtil.getBitmap(getActivity(), R.drawable.home_money));
        imageEmergency.setImageBitmap(ImageUtil.getBitmap(getActivity(),R.drawable.home_clock));

    }

}
