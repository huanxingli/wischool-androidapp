package cn.wischool.ws1app.wischool_androidapp.ui;


import android.support.v4.app.Fragment;

import java.lang.reflect.Field;

import roboguice.fragment.RoboFragment;

/**
 * Created by sunyuhong on 15/9/30.
 */
public class BaseFragment extends RoboFragment {
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
