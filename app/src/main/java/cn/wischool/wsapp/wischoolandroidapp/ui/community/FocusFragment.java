package cn.wischool.wsapp.wischoolandroidapp.ui.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wischool.wsapp.wischoolandroidapp.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class FocusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //动态找到布局文件，再从这个布局中find出TextView对象
        View contextView = inflater.inflate(R.layout.fragment_focus, container, false);

        return contextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
