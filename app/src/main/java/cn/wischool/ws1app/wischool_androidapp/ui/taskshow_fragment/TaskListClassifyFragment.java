package cn.wischool.ws1app.wischool_androidapp.ui.taskshow_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wischool.ws1app.wischool_androidapp.R;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TaskListClassifyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_classify,container,false);
    }
}
