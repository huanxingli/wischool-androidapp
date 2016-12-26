package cn.wischool.ws1app.wischool_androidapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.wischool.ws1app.wischool_androidapp.common.ApplicationUtil;
import cn.wischool.ws1app.wischool_androidapp.infobean.Banner;

/**
 * Created by xing on 2016/12/6.
 */

public class BannerManager {
    private boolean smoothScroll = true;
    private boolean looping = false; //判断是否轮播
    private boolean auto = true;
    private int defaultDelay = 2000;//默认的延迟
    private int defaultPeriod = 5000;//每3秒，就会发送一个message,BannerTask timeTask
    private int scaleSize = 1024;
    private int defaultScrollerSpeed = 1200;
    private int defaultDotsCount = 1;  //默认点的数量
    private int defaultDotWidth = 50;  //默认提示条的宽度
    private int defaultDotHeight = 4; //默认提示条的高度
    private Context context;
    private View mView;
    private ViewPager mViewPagerBanner;
    private LinearLayout mLayoutDots;

    /**
     * 圆点，默认1个
     */
    private List<View> mDots;

    /**
     * banner数据源
     */
    private List<Banner> mDataSource;
    private List<ImageView> mImageViews;
    private int mCurrentItemPosition = 0;//当前滑动到哪一页
    private BannerTask mBannerTask;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (looping)  //当自动轮播的时候
            {
                mCurrentItemPosition = (mCurrentItemPosition + 1);
                mViewPagerBanner.setCurrentItem(mCurrentItemPosition, smoothScroll);
            }
        }
    };

    private int getDotsSize(){
        int size = 1;
        if(mDots != null){
            size = mDots.size();
        }
        return size;
    }

    private BannerManager(){}

    //单例模式
    private static class SingletonHolder{
        private static BannerManager instance = new BannerManager();
    }

    public static BannerManager getInstance(){
        return SingletonHolder.instance;
    }

    //初始化主布局View
    public BannerManager initView(Context context, View view){
        this.mView = view;
        this.context = context;
        return getInstance();
    }

    public BannerManager attachViewPager(int resId){
        if (context == null) {//mView maybe null
            return getInstance();
        }

        mViewPagerBanner = (ViewPager) mView.findViewById(resId);
        mLayoutDots = (LinearLayout) mView.findViewById(R.id.ll_dots);
        mView.setVisibility(View.VISIBLE);

        View dot0 = mView.findViewById(R.id.v_dot0);
        mDots = new ArrayList<>();
        mDots.add(dot0);
        setViewPagerScrollerSpeed(mViewPagerBanner, defaultScrollerSpeed);
        setViewPageChangeListener(mViewPagerBanner);
        return getInstance();
    }

    /**
     * 通过反射的方法，控制滑动速度，避免页面跳转是瞬间闪动
     * @param viewPager
     * @param duration
     */
    private void setViewPagerScrollerSpeed(ViewPager viewPager, int duration) {
        try {
            Field mScroll = ViewPager.class.getDeclaredField("mScroller");
            mScroll.setAccessible(true);
            FixedSpeedScroller speedScroller = new FixedSpeedScroller(context);
            speedScroller.setmDuration(duration);
            mScroll.set(viewPager,speedScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public BannerManager setLocalBanner(List<Banner> source){
        mDataSource = source;
        int dataSourceLen = mDataSource.size();
        mImageViews = new ArrayList<>();
        for (int j = 0; j < dataSourceLen; j++) {
            int resId = mDataSource.get(j).getId();
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(context).load(resId).into(imageView);

            mImageViews.add(imageView);
        }

        int imagesLen = mImageViews.size();
        addDots(imagesLen);
        if (imagesLen == 2) { //当banner数量为2的时候，处理成数量为4的
            for (int k = 0; k < imagesLen; k++) {
                int resId = mDataSource.get(k).getId();
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Picasso.with(context).load(resId).into(imageView);
                mImageViews.add(imageView);
            }
        }

        finish();
        return getInstance();
    }

    private void addDots(int size) {
        if (size > defaultDotsCount) {
            int width = ApplicationUtil.dip2px(defaultDotWidth);
            int height = ApplicationUtil.dip2px(defaultDotHeight);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.setMargins(0, 0, 0, 0);
            for (int i = defaultDotsCount; i < size; i++) {
                View view = new View(mView.getContext());
                view.setLayoutParams(params);
                view.setBackgroundResource(R.drawable.dot_normal);
                view.setVisibility(View.VISIBLE);
                mLayoutDots.addView(view);
                mDots.add(view);
            }
        }
    }

    private void finish() {
        mViewPagerBanner.setAdapter(new BannerAdapter());
        mCurrentItemPosition = mImageViews.size() * (scaleSize >> 1);
        mViewPagerBanner.setCurrentItem(mCurrentItemPosition);
    }

    public void startLoop(boolean auto) {
        if (mView.getVisibility() == View.GONE) {
            return;
        }
        this.auto = auto;
        if (!auto) {
            return;
        }
        if (mImageViews.size() == 1) {//如果只有一张图片的话，就不用自动轮询
            this.auto = false;
            return;
        }
        if (mBannerTask == null) { //若时间调度是空的，就新建一个
            mBannerTask = new BannerTask();
            new Timer().schedule(mBannerTask, defaultDelay, defaultPeriod);
            looping = true;
        }
    }

    public void pauseLoop() {
        if (mView.getVisibility() == View.GONE) {
            return;
        }
        if (!auto) {
            return;
        }
        if (mBannerTask != null) {
            mBannerTask.cancel();
            mBannerTask = null;
        }
        looping = false;
    }

    private void setViewPageChangeListener(ViewPager viewpager){
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int oldVirtualPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int curVirtualPosition = position % (mDataSource.size());
                mDots.get(oldVirtualPosition).setBackgroundResource(R.drawable.dot_normal);
                mDots.get(curVirtualPosition).setBackgroundResource(R.drawable.dot_focused);
                if (!looping) { //当不是自动轮播，需要校正当前位置
                    mCurrentItemPosition = position;
                    mHandler.obtainMessage().sendToTarget();
                }
                oldVirtualPosition = curVirtualPosition;
            }

            /**
             * 手指操作屏幕的时候发生变化,有三种状态（0，1，2）。
             * state ==1的表示默示正在滑动，
             * state==2的表示默示滑动完毕了，
             * state==0的表示默示什么都没做。
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                if (!auto) return;
                if (state == 1) {
                    pauseLoop();
                } else if (state == 0) {
                    //自动轮播状态下被用户打断需要重新start
                    if (!looping) startLoop(true);
                }
            }
        });
    }


    /**
     * ViewPage的适配器
     */
    private class BannerAdapter extends PagerAdapter {

        private View.OnClickListener innerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickPos = (int) v.getTag();
                String encodedLink = mDataSource.get(clickPos).getLink();
                if (!TextUtils.isEmpty(encodedLink)) {
                    switch (encodedLink){
                        case "1":
                            Toast.makeText(v.getContext(),"你点击了1", Toast.LENGTH_LONG).show();
                            break;
                        case "2":
                            Toast.makeText(v.getContext(),"你点击了2", Toast.LENGTH_LONG).show();
                            break;
                        case "3":
                            Toast.makeText(v.getContext(),"你点击了3", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        @Override
        public int getCount() {
            if (getRealCount() < 2) {
                return getRealCount();
            }
            return getRealCount() * scaleSize;
        }

        public int getRealCount() {
            return mImageViews.size();
        }


        //这个方法用来实例化页卡
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int imagePosition = position % getRealCount();
            int dotPosition = position % getDotsSize();
            ImageView bannerView = mImageViews.get(imagePosition);
            ViewParent parent = bannerView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(bannerView);
            }
            bannerView.setTag(dotPosition);
            bannerView.setOnClickListener(innerClickListener);
            container.addView(bannerView);
            return bannerView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object); //don't do this
        }
    }



    private class BannerTask extends TimerTask {

        @Override
        public void run() {
            synchronized (mViewPagerBanner) {
                if (looping)
                    mHandler.obtainMessage().sendToTarget();
            }
        }
    }

    private class FixedSpeedScroller extends Scroller {

        private int mDuration = 3600;//默认的速度

        public int getmDuration() {
            return mDuration;
        }

        public void setmDuration(int mDuration) {
            this.mDuration = mDuration;
        }

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

}
