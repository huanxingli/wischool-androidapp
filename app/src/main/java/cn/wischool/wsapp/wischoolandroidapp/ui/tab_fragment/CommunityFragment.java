package cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.ui.community.ActivityFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.community.FocusFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.community.HotFragment;


/**
 * Created by xiaoxingxing on 2016/5/11.
 */
public class CommunityFragment extends Fragment {

    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[] { "热门", "活动", "关注" };

    private FocusFragment focusFragment = null;
    private ActivityFragment activityFragment = null;
    private HotFragment hotFragment = null;

    private ViewPager pager;
    private TabPageIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.StyledIndicators);
        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_shequ,container,false);

        pager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        focusFragment = new FocusFragment();
        activityFragment = new ActivityFragment();
        hotFragment = new HotFragment();

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);


        indicator.setViewPager(pager);//关联

        return view;
    }

    /**
     * 定义ViewPager的适配器
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = hotFragment;
                    break;
                case 1:
                    fragment = activityFragment;
                    break;
                case 2:
                    fragment = focusFragment;
                    break;
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }
}
