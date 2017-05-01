package cn.wischool.wsapp.wischoolandroidapp.ui.taskshow_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wischool.wsapp.wischoolandroidapp.R;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TaskListSelectFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_select,container,false);
    }
}
